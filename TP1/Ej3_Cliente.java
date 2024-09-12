import java.io.*;
import java.net.*;

public class Ej3_Cliente {
    public static void main(String[] args) throws IOException {
        // Verificar la cantidad de parámetros de la línea de comandos
        if ((args.length != 3) || (Integer.valueOf(args[1]) <= 0)) {
            System.out.println("Se necesitan 3 argumentos: serverhostname port length");
            System.exit(1);
        }

        // Socket para conectarse al servidor
        Socket socketwithserver = null;

        try {
            // Conexión con el servidor
            socketwithserver = new Socket(args[0], Integer.valueOf(args[1]));
        } catch (Exception e) {
            System.out.println("ERROR conectando");
            System.exit(1);
        }

        // Streams de entrada y salida para comunicarse con el servidor
        DataInputStream fromserver;
        DataOutputStream toserver;

        fromserver = new DataInputStream(socketwithserver.getInputStream());
        toserver = new DataOutputStream(socketwithserver.getOutputStream());

        // Buffer para usar en las comunicaciones
        byte[] buffer;

        // Obtener entrada del usuario
        //Console console = System.console();
        //String inputline = console.readLine("Por favor, ingrese el mensaje: ");
        // Crear un string de x caracteres 'A' (cada uno de 1 byte en UTF-8)
        byte[] bytes = new byte[Integer.valueOf(args[2])];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = 'A'; // El carácter 'A' en UTF-8 ocupa 1 byte
        }

        // Convertir el array de bytes en un string
        String mensaje = new String(bytes, "UTF-8");
        buffer = mensaje.getBytes();

        // Tomar el tiempo antes de enviar los datos
        long startTime = System.nanoTime();

        // Enviar datos al servidor
        toserver.write(buffer, 0, buffer.length);

        // Recibir respuesta del servidor
        buffer = new byte[256];
        fromserver.read(buffer);

        // Tomar el tiempo después de recibir la respuesta
        long endTime = System.nanoTime();

        // Calcular el tiempo total de comunicación en milisegundos
        long duration = (endTime - startTime) / 1_000_000;

        // Mostrar el mensaje recibido del servidor y el tiempo de comunicación
        String resp = new String(buffer);
        //System.out.println("Respuesta del servidor: " + resp.trim());
        System.out.println(duration);

        // Cerrar streams y sockets
        fromserver.close();
        toserver.close();
        socketwithserver.close();
    }
}
