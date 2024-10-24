package com.example.chat;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class Punto5Client {

    public static void main(String[] args) throws InterruptedException {
        // Toma el tamaño del mensaje como parámetro
        int messageSize = Integer.parseInt(args[0]);

        // Crear el canal gRPC
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        Punto5ServiceGrpc.Punto5ServiceBlockingStub stub = Punto5ServiceGrpc.newBlockingStub(channel);

        // Generar mensaje de tamaño específico
        byte[] message = new byte[messageSize];
        MensaggeRequest request = MensaggeRequest.newBuilder()
                .setMessage(com.google.protobuf.ByteString.copyFrom(message))
                .build();

        // Medir el tiempo de respuesta
        long startTime = System.nanoTime();
        MensaggeResponse response = stub.measure(request);
        long endTime = System.nanoTime();

        // Imprimir el tiempo en milisegundos
        long duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("Tiempo para mensaje de " + messageSize + " bytes: " + duration + " ms");

        // Cerrar el canal
        channel.shutdown();
    }
}

