import jade.core.Agent;
import jade.core.Location;
import jade.core.ContainerID;

public class AgenteB extends Agent {
    private ContainerID origenContainer;

    // Se ejecuta durante la creación
    protected void setup() {
        Location origen = here();
        origenContainer = new ContainerID(origen.getName(), null); 
        System.out.println("\n\nNombre del AgenteB: " + getLocalName());
        System.out.println("Localización Actual del AgenteB: " + origen.getID() + "\n\n");
    }

    // Se ejecuta después de la migración
    protected void afterMove() {
        try {
            Location nuevaUbicacion = here();
            System.out.println("\n\nNombre del AgenteB: " + getLocalName());
            System.out.println("Nueva Localización: " + nuevaUbicacion.getID() + "\n\n");

            // Espera antes de migrar de regreso
            Thread.sleep(2000);

            // Volver al contenedor de origen
            System.out.println("Regresando al contenedor original: " + origenContainer.getID());
            doMove(origenContainer);
        } catch (Exception e) {
            System.err.println("Error durante la migración: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
