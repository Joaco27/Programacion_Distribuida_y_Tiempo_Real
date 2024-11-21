import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.behaviours.TickerBehaviour;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import java.io.*;

public class AgenteA extends Agent {
    private long startTime;
    private AgentController [] agentes = new AgentController[5];
    private ContainerID contenedorDestino = new ContainerID("Main-Container", null); 
    private ContainerController contenedorActual = getContainerController();


    @Override
    protected void setup() {

        for (int i=0; i<5; i++) {
            try {
                agentes[i] = contenedorActual.createNewAgent("AgenteB_" + i, "AgenteB", null);
                agentes[i].start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Iniciar recopilación periódica con un TickerBehaviour
        addBehaviour(new TickerBehaviour(this, 6000) { // Cada 6 segundos
            @Override
            protected void onTick() {
                startTime = System.currentTimeMillis();

                // Limpiar archivo de resultados
                limpiarArchivo("resultados.txt");

               for (int i=0; i<5; i++) {
                    try {
						agentes[i].move(contenedorDestino);
					} catch (StaleProxyException e) {
						e.printStackTrace();
					}
                }

                

                verificarResultados();
            }
        });
    }

    private void limpiarArchivo(String archivo) {
        try (PrintWriter writer = new PrintWriter(new File(archivo))) {
            writer.print("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void verificarResultados() {
        try (BufferedReader reader = new BufferedReader(new FileReader("resultados.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println("Resultado recibido: " + linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo total de recopilación: " + (endTime - startTime) + " ms");
    }
}
