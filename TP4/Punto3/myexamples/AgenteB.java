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

        // Crear comportamiento para esperar antes de realizar la tarea
        addBehaviour(new SimpleBehaviour() {
            private boolean finished = false;

            @Override
            public void action() {
                try {
                    block(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

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
                String datos = "Nombre PC: " + nombrePC + ", Carga: " + carga + ", Memoria: " + memoria;

                // Escribir los datos en el archivo
                escribirArchivo("resultados.txt", datos);
                
                doMove(cOrigen);
                
                finished = true;

            }

            @Override
            public boolean done() {
                return finished;
            }
        });
    }

    private void escribirArchivo(String archivo, String datos) {
        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(datos + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
