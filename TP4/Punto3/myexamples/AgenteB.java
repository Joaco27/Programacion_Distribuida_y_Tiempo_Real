import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.Location;
import jade.core.behaviours.SimpleBehaviour;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class AgenteB extends Agent {

    private ContainerID cOrigen;
    private ContainerID cDestino;
    private String datos="";

    @Override
    protected void setup() {
        // Guardar el contenedor de origen
        Location origen = here();
        System.out.println("Contenedor Origen de " + getLocalName());
        cOrigen = new ContainerID(origen.getName(), null);
    }

    @Override
    protected void afterMove() {
        // Guardar el contenedor de destino
        Location origen = here();
        cDestino = new ContainerID(origen.getName(), null);
        
        // Pregunto si donde me muevo es el contenedor de origen
        if (cDestino.getName().equals(cOrigen.getName())) {
        	if(!datos.equals("")) {
                escribirArchivo("resultados.txt", datos);
                datos="";
        	}
        }
        else {
        	OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
            String carga = String.valueOf(osBean.getSystemLoadAverage());
            String memoria = String.valueOf(Runtime.getRuntime().totalMemory());
            String nombrePC = "";
            try {
                nombrePC = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            
	         // Crear los datos para guardar en el archivo
	         datos = "Nombre PC: " + nombrePC + ", Carga: " + carga + ", Memoria: " + memoria;
	         
	         //Vuelvo al Container de origen
	         doMove(cOrigen);
        }


                
    }

    private synchronized  void escribirArchivo(String archivo, String datos) {
        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(getLocalName() + ": " + datos + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
