/*
 * Client.java
 * Solo envía datos leídos desde la entrada estándar y recibe de vuelta algunos datos del servidor
 *
 * uso:
 * java Client serverhostname port
 */

 import java.io.*;
 import java.net.*;
 
 public class Ej2_Cliente
 {
   public static void main(String[] args) throws IOException
   {
     /* Verifica la cantidad de parámetros en la línea de comandos */
     if ((args.length != 3) || (Integer.valueOf(args[1]) <= 0) )
     {
       System.out.println("Se necesitan 3 argumentos: serverhostname port length");
       System.exit(1);
     }
 
     /* El socket para conectarse al servidor de eco */
     Socket socketwithserver = null;
 
     try /* Conexión con el servidor */
     { 
       socketwithserver = new Socket(args[0], Integer.valueOf(args[1]));
     }
     catch (Exception e)
     {
       System.out.println("ERROR al conectar");
       System.exit(1);
     } 
 
     /* Streams desde/hacia el servidor */
     DataInputStream  fromserver;
     DataOutputStream toserver;
 
     /* Streams para E/S a través del socket conectado */
     fromserver = new DataInputStream(socketwithserver.getInputStream());
     toserver   = new DataOutputStream(socketwithserver.getOutputStream());
 
     /* Buffer para usar en las comunicaciones (y su longitud) */
     byte[] buffer;
     
     /* Obtener alguna entrada del usuario */
    //  Console console  = System.console();
    //  String inputline = console.readLine("Por favor, ingrese el mensaje: ");


    // Crear un string de x caracteres 'A' 
    byte[] bytes = new byte[Integer.valueOf(args[2])];
    for (int i = 0; i < bytes.length; i++) {
        bytes[i] = 'A'; 
    }
    bytes[0] = 'F';
    bytes[bytes.length - 1] = 'F';

    // Convertir el array de bytes en un string
    String mensaje = new String(bytes, "UTF-8");
     /* Obtener los bytes... */
     buffer = mensaje.getBytes();
 
     /* Enviar los datos leídos al servidor */
     toserver.write(buffer, 0, buffer.length);
     
     /* Recibir datos de vuelta del servidor (obtener espacio) */
     buffer = new byte[256];
     fromserver.read(buffer);
 
     /* Mostrar los datos recibidos del servidor */
     String resp = new String(buffer);
     //System.out.println(resp);
     
     fromserver.close();
     toserver.close();
     socketwithserver.close();
   }
 }
 