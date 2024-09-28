#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <string.h>  // Para strlen y otras funciones de cadenas
#include <unistd.h>  // Para read, write y otras funciones de E/S
#include <stdlib.h>  // Para exit y otras funciones estándar
#include <time.h>    // Para clock()

void error(const char *msg)
{
    perror(msg);
    exit(1);
}

int main(int argc, char *argv[])
{
    int sockfd, portno, n;
    struct sockaddr_in serv_addr;
    struct hostent *server;

    char *hostname;
    int message_size;
    char *message;
    char buffer[256];
    
    if (argc < 4) {
        fprintf(stderr,"usage %s hostname port message_size\n", argv[0]);
        exit(1);
    }

    hostname = argv[1];
    portno = atoi(argv[2]);
    message_size = atoi(argv[3]);

    // Crear el mensaje
    message = malloc(message_size);
    if (message == NULL) {
        error("ERROR allocating memory");
    }
    memset(message, 'A', message_size);

    // Crear el descriptor del socket
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0) 
        error("ERROR opening socket");

    // Obtener la dirección del servidor
    server = gethostbyname(hostname);
    if (server == NULL) {
        fprintf(stderr,"ERROR, no such host\n");
        exit(0);
    }

    // Limpiar la estructura serv_addr
    bzero((char *) &serv_addr, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    bcopy((char *)server->h_addr, (char *)&serv_addr.sin_addr.s_addr, server->h_length);
    serv_addr.sin_port = htons(portno);

    // Conectar al servidor
    if (connect(sockfd, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0) 
        error("ERROR connecting");

    // Medir el tiempo
    clock_t start_time = clock();

    // Enviar el mensaje
    n = write(sockfd, message, message_size);
    if (n < 0) 
         error("ERROR writing to socket");

    // Esperar la respuesta
    bzero(buffer, 256);
    n = read(sockfd, buffer, 255);
    if (n < 0) 
         error("ERROR reading from socket");

    clock_t end_time = clock();
    double duration = (double)(end_time - start_time) / CLOCKS_PER_SEC * 1000; // en milisegundos

    printf("Tiempo de comunicación: %.2f ms\n", duration);

    // Liberar memoria y cerrar el socket
    free(message);
    close(sockfd);

    return 0;
}