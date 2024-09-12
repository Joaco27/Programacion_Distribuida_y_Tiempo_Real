/*
 * EchoServer.java
 * Solo recibe algunos datos y envía un "mensaje" de vuelta a un cliente
 *
 * Uso:
 * java Server port
 */

 import java.io.*;
 import java.net.*;
 
 public class Ej2_Servidor
 {
   public static void main(String[] args) throws IOException
   {
     /* Verifica la cantidad de parámetros en la línea de comandos */
     if ((args.length != 2) || (Integer.valueOf(args[0]) <= 0) )
     {
       System.out.println("Se necesitan 2 argumentos: port length");
       System.exit(1);
     }
 
     /* El socket del servidor */
     ServerSocket serverSocket = null;    
     try
     {
       serverSocket = new ServerSocket(Integer.valueOf(args[0]));
     } 
     catch (Exception e)
     {
       System.out.println("Error en el socket del servidor");
       System.exit(1);
     }
 
     /* El socket que se creará en la conexión con el cliente */
     Socket connected_socket = null;
 
     try /* Esperar una conexión con un cliente */
     {
       connected_socket = serverSocket.accept();
     }
     catch (IOException e)
     {
       System.err.println("Error en Accept");
       System.exit(1);
     }
 
     /* Streams desde/hacia el cliente */
     DataInputStream fromclient;
     DataOutputStream toclient;
 
     /* Obtener los streams de E/S del socket conectado */
     fromclient = new DataInputStream(connected_socket.getInputStream());
     toclient   = new DataOutputStream(connected_socket.getOutputStream());
 
     /* Buffer para usar en las comunicaciones (y su longitud) */
     byte[] buffer;
     buffer = new byte[Integer.valueOf(args[1])];
     
     /* Recibir datos del cliente */
     int bytesRead = fromclient.read(buffer);
 
     /* Convertir a cadena */
     String str = new String(buffer, 0, bytesRead);
 
    //  System.out.println("Aqui esta el mensaje: " +  str);
    System.out.println("El buffer pesa: " +  buffer.length);
    System.out.println("El mensaje pesa: " +  str.getBytes().length);
    System.out.println("El primer caracter es: " + str.charAt(0) + " y el ultimo: " + str.charAt(str.length() - 1) );

 
     /* Cadena fija para el cliente */
     String strresp = "Recibi tu mensaje";
 
     //System.out.println("strrsp " + strresp);
 
     buffer = strresp.getBytes();
 
     /* Enviar los bytes de vuelta */
     toclient.write(buffer, 0, buffer.length);
 
     /* Cerrar todo lo relacionado con la conexión del cliente */
     fromclient.close();
     toclient.close();
     connected_socket.close();
     serverSocket.close();
   }
 }
 