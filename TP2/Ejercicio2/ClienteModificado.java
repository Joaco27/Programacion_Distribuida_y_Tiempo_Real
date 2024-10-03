
import java.io.*;
import java.net.*;

public class ClienteModificado {
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
        byte[] buffer = new byte[Integer.valueOf(args[2])];

        // Preparar el mensaje
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = 'A'; // El carácter 'A' en UTF-8 ocupa 1 byte
        }

        long t0 = System.currentTimeMillis(); // Tomar el tiempo antes de enviar los datos

        // Enviar datos al servidor
        toserver.write(buffer, 0, buffer.length);
        toserver.flush();  // Asegurarse de que los datos se envíen

        // Recibir respuesta del servidor
        int bytesRead = 0;
        int totalBytesRead = 0;

        // Leer hasta completar el buffer
        while (totalBytesRead < buffer.length && (bytesRead = fromserver.read(buffer, totalBytesRead, buffer.length - totalBytesRead)) != -1) {
            totalBytesRead += bytesRead;
        }

        long t1 = System.currentTimeMillis(); // Tomar el tiempo después de recibir la respuesta

        long one_way_time = (t1 - t0) / 2; // Calcular el tiempo de comunicación de ida
        // System.out.println("Tiempo de ida y vuelta: " + (t1 - t0) + " ms");
        // System.out.println("Tiempo de ida: " + one_way_time + " ms");
        System.out.println(one_way_time);

        // Cerrar streams y socket
        fromserver.close();
        toserver.close();
        socketwithserver.close();
    }
}
