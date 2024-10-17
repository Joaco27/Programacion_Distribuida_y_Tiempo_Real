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

  public static int calcularChecksum(byte[] data) {
    int checksum = 0;
    for (byte b : data) {
        checksum += (b & 0xFF);
    }
    return checksum;
}
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
     fromclient.read(buffer);

     // Leer el checksum recibido
     int checksumRecibido = fromclient.readInt();

     // Calcular el checksum de los datos recibidos
     int checksumCalculado = calcularChecksum(buffer);
 
     /* Convertir a cadena */
     String str = new String(buffer);

    boolean cumple = (str.charAt(0) == 'F' && str.charAt(str.length() - 1) == 'F'
    && checksumCalculado==checksumRecibido);

    System.out.println("El mensaje cumple con integridad y completitud: " + cumple);
    System.out.println("Checksum calculado: "+checksumCalculado);
    System.out.println("Checksum recibido: "+checksumRecibido);


 
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
 