package com.example.chat;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import app.pdytr.ChatServiceGrpc;
import app.pdytr.ChatServiceProto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class ChatClientEj4 {

    public static void main(String[] args) {
        try (FileWriter fileWriter = new FileWriter("Resultados.txt", false)) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        int [] cantClientes = new int[5];
        cantClientes[0] = 1;
        cantClientes[1] = 5;
        cantClientes[2] = 10;
        cantClientes[3] = 20;
        cantClientes[4] = 50;

        for (int j=0; j<cantClientes.length; j++){
            String contenido = "Cantidad de Clientes: " + cantClientes[j];

            File archivo = new File("Resultados.txt");
        
            try (FileWriter writer = new FileWriter(archivo, true)) {
                writer.write(contenido + "\n"); 
            } catch (IOException e) {
                System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
            }
            
            Thread[] hilos = new Thread[cantClientes[j]];
            for (int i = 0; i < cantClientes[j]; i++) {
                String username = "Cliente" + (i + 1);
                String message = "Hola, soy " + username;
                hilos[i] = new Thread(new ChatClientThread(username, message));
            }

            for (int i = 0; i < cantClientes[j]; i++) {
                hilos[i].start();
            }

            try {
                for (int i = 0; i < cantClientes[j]; i++) {
                    hilos[i].join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class ChatClientThread implements Runnable {
    private String username;
    private String messageText;

    public ChatClientThread(String username, String messageText) {
        this.username = username;
        this.messageText = messageText;
    }

    @Override
    public void run() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();
        
        try {
            final ChatServiceGrpc.ChatServiceBlockingStub stub = ChatServiceGrpc.newBlockingStub(channel);

            User user = User.newBuilder()
                    .setName(username)
                    .build();

            ConnectRequest connectRequest = ConnectRequest.newBuilder()
                    .setUser(user)
                    .build();

            ConnectResponse connectResponse = stub.connect(connectRequest);
            User usuario = connectResponse.getUser();
            // System.out.println(connectResponse.getWelcome());

            Message message = Message.newBuilder()
                    .setUser(usuario)
                    .setContent(messageText)
                    .build();

            SendMessageRequest sendRequest = SendMessageRequest.newBuilder()
                    .setUser(usuario)
                    .setMessage(message)
                    .build();

            long startTime = System.currentTimeMillis();

            SendMessageResponse response = stub.sendMessage(sendRequest);

            long endTime = System.currentTimeMillis();

            long responseTime = endTime - startTime;

            DisconnectRequest disconnectRequest = DisconnectRequest.newBuilder()
                    .setUser(usuario)
                    .build();
            DisconnectResponse disconnectResponse = stub.disconnect(disconnectRequest);
            // System.out.println(disconnectResponse.getGoodbye());

            String contenido = "Tiempo de respuesta es: " + responseTime + " ms";

            File archivo = new File("Resultados.txt");
        
            try (FileWriter writer = new FileWriter(archivo, true)) {
                writer.write(contenido + "\n"); 
            } catch (IOException e) {
                System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
            }

        } catch (StatusRuntimeException e) {
            System.err.println("Error en la comunicación: " + e.getStatus());
        } finally {
            channel.shutdownNow();
        }
    }
}
