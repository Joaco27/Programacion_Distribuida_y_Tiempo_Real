import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.behaviours.TickerBehaviour;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

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
                TickerBehaviour tickerInterno = new TickerBehaviour(myAgent, 2000) { // Cada 2 segundos
                    private int segundos = 0;
                    
                    if (!enEjecucion) {
                        enEjecucion = true; // Indicar que se está ejecutando una ronda
                        startTime = System.currentTimeMillis();
                        agentesTerminados.clear(); // Reiniciar el registro de finalización

                        // Mover a los AgentesB a nuevos contenedores
                        for (int i = 0; i < agentesB.length; i++) {
                            try {
                                agentesB[i].move(contenedores[(i + 1) % 5].getContainerID());
                            } catch (StaleProxyException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    protected void onTick() {
                        segundos++;

                        // Detener el ticker interno después de 5 ejecuciones
                        if (segundos == 5) {
                            stop();
                            System.out.println("   Ticker interno detenido.");
                        }
                    }
                };

                // Agregar el ticker interno al agente
                myAgent.addBehaviour(tickerInterno);
            }
        });
    }
}
