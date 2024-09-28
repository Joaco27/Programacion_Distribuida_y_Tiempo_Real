#include <stdio.h>
#include <stdlib.h>   // Para exit, atoi y otras funciones estándar
#include <string.h>   // Para bzero y otras funciones de cadenas
#include <unistd.h>   // Para read, write y otras funciones de E/S
#include <sys/types.h> 
#include <sys/socket.h>
#include <netinet/in.h>

void error(const char *msg) {
    perror(msg);
    exit(1);
}

int main(int argc, char *argv[]) {
    int sockfd, newsockfd, portno;
    socklen_t clilen;
    struct sockaddr_in serv_addr, cli_addr;
    int n;
    int message_size;

    if (argc < 3) {
        fprintf(stderr, "usage %s port size\n", argv[0]);
        exit(1);
    }

    // Toma el número de puerto y el tamaño del mensaje de los argumentos
    portno = atoi(argv[1]);
    message_size = atoi(argv[2]);

    // Crea el descriptor del socket para la conexión
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0) 
        error("ERROR opening socket");

    // Limpia la estructura serv_addr
    bzero((char *) &serv_addr, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = INADDR_ANY;
    serv_addr.sin_port = htons(portno);

    // Vincula el file descriptor con la dirección y el puerto
    if (bind(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0) 
        error("ERROR on binding");

    // Establece la cantidad que pueden esperar mientras se maneja una conexión
    listen(sockfd, 5);
    clilen = sizeof(cli_addr);

    // Se bloquea a esperar una conexión
    newsockfd = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen);
    if (newsockfd < 0) 
        error("ERROR on accept");

    // Crea un buffer del tamaño especificado
    char *buffer = (char *)malloc(message_size);
    if (buffer == NULL) {
        error("ERROR allocating memory");
    }
    bzero(buffer, message_size);

    // Lee el mensaje del cliente
    n = read(newsockfd, buffer, message_size);
    if (n < 0) 
        error("ERROR reading from socket");
    //printf("Here is the message: %s\n", buffer);

    // Responde al cliente
    const char *response = "I got your message";
    n = write(newsockfd, response, strlen(response));
    if (n < 0) 
        error("ERROR writing to socket");

    // Libera el buffer y cierra los sockets
    free(buffer);
    close(newsockfd);
    close(sockfd);

    return 0;
}