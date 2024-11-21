import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.behaviours.TickerBehaviour;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import jade.core.Runtime;

import java.io.*;

public class AgenteA extends Agent {
    private long startTime;
    private final int cantAgentes = 5;
    private AgentController[] agentes = new AgentController[cantAgentes];
    private boolean finRonda = true;
    private int ronda = 0;

    @Override
    protected void setup() {
        try {
            // Crear contenedores
            ContainerController[] contenedores = new ContainerController[cantAgentes];
            for (int i = 0; i < cantAgentes; i++) {
                Profile profile = new ProfileImpl();
                profile.setParameter(Profile.MAIN_HOST, "localhost");
                profile.setParameter(Profile.CONTAINER_NAME, "Container" + i);
                contenedores[i] = Runtime.instance().createAgentContainer(profile); 
            }

            // Crear agentes
            for (int i = 0; i < cantAgentes; i++) {
                agentes[i] = getContainerController().createNewAgent("AgenteB_" + i, "AgenteB", null);
                agentes[i].start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Ticker para mover agentes y medir tiempos
        addBehaviour(new TickerBehaviour(this, 10000) { // Cada 10 segundos
            @Override
            protected void onTick() {
                if (finRonda) {
                    finRonda = false; // Marcar el inicio de una nueva ronda
                    limpiarArchivo();
                    startTime = System.currentTimeMillis();

                    // Mover agentes a sus respectivos contenedores
                    try {
                        for (int i = 0; i < cantAgentes; i++) {
                            ContainerID destino = new ContainerID("Container" + i, null);
                            agentes[i].move(destino);
                        }
                    } catch (StaleProxyException e) {
                        e.printStackTrace();
                    }

                    // Agregar Ticker para verificar resultados
                    addBehaviour(new TickerBehaviour(myAgent, 1000) { // Cada 1 segundo
                        @Override
                        protected void onTick() {
                            if (verificarResultados()) {
                                long endTime = System.currentTimeMillis();
                                String resultado = "Finalizo Ronda " + ronda++ + ". Tiempo total: " + (endTime - startTime) + " ms";
                                System.out.println(resultado);
                                finRonda = true;
                                escribirArchivo(resultado);
                                stop();
                            }
                        }
                    });
                }
            }
        });
    }

    private void limpiarArchivo() {
        try (PrintWriter writer = new PrintWriter(new File("resultados.txt"))) {
            writer.print(""); // Limpiar archivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean verificarResultados() {
        int lineasLeidas = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("resultados.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineasLeidas++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineasLeidas == cantAgentes;
    }
    
    private String contenidoResult() {
    	String lineasLeidas="";
    	try (BufferedReader reader = new BufferedReader(new FileReader("resultados.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineasLeidas+=linea+"\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	return lineasLeidas;
    }
    
    private  void escribirArchivo(String datos) {
        try (FileWriter writer = new FileWriter("history_log.txt", true)) {
        	writer.write(contenidoResult() + "\n");
            writer.write(datos + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
