package com.example.chat;

import app.pdytr.MessageProto.*; 
import app.pdytr.Punto5ServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Punto5PruebaClient {

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        Punto5ServiceGrpc.Punto5ServiceBlockingStub stub = Punto5ServiceGrpc.newBlockingStub(channel);

        String mensajeInicial = "Inicio de las mediciones."; 
        MessageRequest requestInicial = MessageRequest.newBuilder()
                .setMessage(mensajeInicial)
                .build();
        stub.measure(requestInicial); 

        int tamaño = 10;  
        int repeticiones = 10;

        for (int i = 1; i <= 6; i++) {
            double[] datos = new double[repeticiones];
            double sumaTotal = 0;
            double sumaDiferenciasCuadradas = 0;

            StringBuilder sb = new StringBuilder();

            int numberOfCharacters = tamaño;

            for (int k = 0; k < numberOfCharacters; k++) {
                sb.append('A');
            }

            String resultString = sb.toString();


            MessageRequest request = MessageRequest.newBuilder()
                    .setMessage(resultString)
                    .build();

            for (int j = 0; j < repeticiones; j++) {
                long startTime = System.currentTimeMillis();

                MessageResponse response = stub.measure(request);
                long endTime = System.currentTimeMillis();

                long duration = endTime - startTime;

                datos[j] = duration;
                sumaTotal += duration;

                System.out.println("Tiempo para mensaje de " + tamaño + " bytes: " + duration + " ms");
            }

            double promedio = sumaTotal / repeticiones;

            for (double dato : datos) {
                sumaDiferenciasCuadradas += Math.pow(dato - promedio, 2);
            }

            // Calcular la varianza
            double varianza = sumaDiferenciasCuadradas / repeticiones;

            System.out.println("Desviación estándar de " + tamaño + " es: " + Math.sqrt(varianza));
            System.out.println("Promedio de " + tamaño + " bytes: " + promedio + " ms");

            tamaño *= 10; 
        }

        channel.shutdown();
    }
}
