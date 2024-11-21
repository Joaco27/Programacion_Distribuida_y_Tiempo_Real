import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.behaviours.TickerBehaviour;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class AgenteTicker extends Agent {
	private long startTime;
    private AgentController [] agentes = new AgentController[5];
    private ContainerID contenedorDestino = new ContainerID("Main-Container", null); 
    private ContainerController contenedorActual = getContainerController();
    private boolean ejecucion = false;

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

    	addBehaviour(new TickerBehaviour(this, 10000) { // Cada 10 segundos
            @Override
            protected void onTick() {
                startTime = System.currentTimeMillis();

                try {
                    for (AgentController agenteB : agentes) {
                        agenteB.move(new jade.core.ContainerID("Main-Container", null));
                    }
                } catch (StaleProxyException e) {
                    e.printStackTrace();
                }

                addBehaviour(new TickerBehaviour(myAgent, 1000) { // Cada 1 segundo
                    @Override
                    protected void onTick() {

                        if (verificarResultados(5)) {
                            long endTime = System.currentTimeMillis();
                            System.out.println("Todos los agentes han terminado. Tiempo total: " + (endTime - startTime) + " ms");

                            stop();
                        }
                    }
                });
            }
        });
    }
    
    private boolean verificarResultados(int cantidadAgentes) {
        int lineasLeidas = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("resultados.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineasLeidas++;
                System.out.println("Resultado recibido: " + linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineasLeidas==cantidadAgentes;
    }

}
