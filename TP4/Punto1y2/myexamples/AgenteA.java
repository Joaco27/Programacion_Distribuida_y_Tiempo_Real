import jade.core.Agent;
import jade.core.Location;
import jade.core.ContainerID;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class AgenteA extends Agent {

    // Se ejeuta en la creacion del agente
    protected void setup() {
        Location origen = here();
        System.out.println("\n\nNombre del AgenteA: " + getLocalName());
        System.out.println("Locacion del AgenteA: " + origen.getID() + "\n\n");

        try{
            ContainerController container = getContainerController();
            AgentController nuevoAgente = container.createNewAgent("AgenteB","AgenteB",null);
            nuevoAgente.start();
            ContainerID destino = new ContainerID("Main-Container", null);
            nuevoAgente.move(destino);

        } catch(Exception e){
            System.err.println("Error al crear o mover AgenteB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Se ejcuta despues de una migracion
    protected void afterMove() {
        Location origen = here();
        System.out.println("\n\nHello, migrated agent with local name " + getLocalName());
        System.out.println("And full name... " + getName());
        System.out.println("And in location " + origen.getID() + "\n\n");
    }
}
