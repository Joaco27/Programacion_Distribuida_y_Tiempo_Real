package com.example.chat;

import com.example.chat.Punto5ServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class Punto5Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080)
                .addService(new Punto5ServiceImpl())  
                .build();

        System.out.println("Servidor gRPC iniciado en el puerto 8080.");
        server.start();
        server.awaitTermination();
    }
}
