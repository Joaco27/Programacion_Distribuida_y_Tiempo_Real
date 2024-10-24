package com.example.chat;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class Punto5Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Crear el servidor y añadir la implementación del servicio
        Server server = ServerBuilder.forPort(8080)
                .addService(new Punto5ServiceImpl())  // Usar la clase de implementación
                .build();

        System.out.println("Servidor gRPC iniciado en el puerto 8080.");
        server.start();
        server.awaitTermination();
    }
}
