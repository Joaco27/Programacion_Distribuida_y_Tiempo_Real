import java.io.*;
import java.net.*;

public class Servidor {
   public static void main(String[] args) throws IOException
   {
     /* Check the number of command line parameters */
     if (args.length != 2) {
       System.out.println("2 arguments needed: port length");
       System.exit(1);
     }

     /* The server socket */
     ServerSocket serverSocket = null;    
     try {
       serverSocket = new ServerSocket(Integer.valueOf(args[0]));
     } catch (Exception e) {
       System.out.println("Error on server socket");
       System.exit(1);
     }

     /* The socket to be created on the connection with the client */
     Socket connected_socket = null;

     try /* To wait for a connection with a client */ {
       connected_socket = serverSocket.accept();
     } catch (IOException e) {
       System.err.println("Error on Accept");
       System.exit(1);
     }

     /* Streams from/to client */
     DataInputStream fromclient;
     DataOutputStream toclient;

     /* Get the I/O streams from the connected socket */
     fromclient = new DataInputStream(connected_socket.getInputStream());
     toclient   = new DataOutputStream(connected_socket.getOutputStream());

     /* Buffer to use with communications (and its length) */
     byte[] buffer = new byte[Integer.valueOf(args[1])];

    try {
      // Recibir datos del cliente
      fromclient.read(buffer);

      // Convertir a string
      //String str = new String(buffer);

      // Preparar la respuesta
      String strresp = "I got your message";
      buffer = strresp.getBytes();

      // Enviar la respuesta al cliente
      toclient.write(buffer, 0, buffer.length);
    } catch (IOException e) {
      System.err.println("Error during communication");
    }

     /* Close everything related to the client connection */
     fromclient.close();
     toclient.close();
     connected_socket.close();
     serverSocket.close();
   }
}
