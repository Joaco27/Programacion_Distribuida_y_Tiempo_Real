import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) throws IOException {
        // Verificar la cantidad de parámetros de la línea de comandos
        if (args.length != 3) {
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

        // Preparar el mensaje
        byte[] bytes = new byte[Integer.valueOf(args[2])];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = 'A'; // El carácter 'A' en UTF-8 ocupa 1 byte
        }
        String mensaje = new String(bytes, "UTF-8");
        buffer = mensaje.getBytes();

        long startTime = System.currentTimeMillis(); // Tomar el tiempo antes de enviar los datos

        // Enviar datos al servidor
        toserver.write(buffer, 0, buffer.length);

        // Recibir respuesta del servidor
        fromserver.read(buffer);

        long endTime = System.currentTimeMillis(); // Tomar el tiempo después de recibir la respuesta

        long elapsedTime = endTime - startTime;

        System.out.println(elapsedTime);

        // Cerrar streams y socket
        fromserver.close();
        toserver.close();
        socketwithserver.close();
    }
}
