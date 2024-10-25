package com.example.chat;

import app.pdytr.MessageProto.*; 
import app.pdytr.Punto5ServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.nio.charset.StandardCharsets;


import java.util.concurrent.TimeUnit;

public class Punto5Client {

    public static void main(String[] args) throws InterruptedException {
        int messageSize = Integer.parseInt(args[0]);

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        Punto5ServiceGrpc.Punto5ServiceBlockingStub stub = Punto5ServiceGrpc.newBlockingStub(channel);

        
        StringBuilder sb = new StringBuilder();

        int numberOfCharacters = messageSize; 

        for (int i = 0; i < numberOfCharacters; i++) {
            sb.append('A'); 
        }

        String resultString = sb.toString();

        MessageRequest request = MessageRequest.newBuilder()  
                .setMessage(resultString)
                .build();

        long startTime = System.currentTimeMillis();
        MessageResponse response = stub.measure(request);  
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;
        System.out.println("Tiempo para mensaje de " + messageSize + " bytes: " + duration + " ms");

        channel.shutdown();
    }
}
