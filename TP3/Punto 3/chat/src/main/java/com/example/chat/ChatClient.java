package com.example.chat;

import java.util.Scanner;
import app.pdytr.ChatServiceGrpc;
import app.pdytr.ChatServiceProto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ChatClient {
    public static String formatearTimeStamp(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return "[ " + dateTime.format(formatter) + " ] ";
    }

    public static void main(String[] args) {
        final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        final ChatServiceGrpc.ChatServiceBlockingStub stub = ChatServiceGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su usuario:");
        String username = scanner.nextLine();

        User user = User.newBuilder()
                .setName(username)
                .build();

        try {
            ConnectRequest connectRequest = ConnectRequest.newBuilder()
                    .setUser(user)
                    .build();

            ConnectResponse connectResponse = stub.connect(connectRequest);
            User usuario = connectResponse.getUser();
            System.out.println(connectResponse.getWelcome());

            // Captura de interrupción (Ctrl+C)
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    DisconnectRequest disconnectRequest = DisconnectRequest.newBuilder()
                            .setUser(user)
                            .build();
                    DisconnectResponse disconnectResponse = stub.disconnect(disconnectRequest);
                    System.out.println(disconnectResponse.getGoodbye());
                    channel.shutdown();
                } catch (StatusRuntimeException e) {
                    System.err.println("Desconectando del Servidor");
                }
            }));

            final boolean[] isRunning = {true};
            Thread currentThread = Thread.currentThread(); // Para interrumpirlo más adelante

            // Hilo para enviar mensajes
            Thread sendThread = new Thread(() -> {
                while (isRunning[0]) {
                    try {
                        if (System.in.available() > 0) {  // Para evitar bloqueo en nextLine
                            String messageText = scanner.nextLine();

                            if (messageText.equalsIgnoreCase("/history")) {
                                HistoryRequest historyRequest = HistoryRequest.newBuilder().setUser(usuario).build();
                                HistoryResponse historyResponse = stub.history(historyRequest);
                                System.out.println("Historial de mensajes:");
                                System.out.println(historyResponse.getMessages());
                            } else if (messageText.equalsIgnoreCase("/disconnect")) {
                                DisconnectRequest disconnectRequest = DisconnectRequest.newBuilder().setUser(usuario).build();
                                DisconnectResponse disconnectResponse = stub.disconnect(disconnectRequest);
                                System.out.println(disconnectResponse.getGoodbye());
                                isRunning[0] = false;
                                currentThread.interrupt(); // Interrumpir la espera en el hilo principal
                            } else {
                                Message message = Message.newBuilder()
                                        .setUser(usuario)
                                        .setContent(messageText)
                                        .build();

                                SendMessageRequest sendRequest = SendMessageRequest.newBuilder()
                                        .setUser(usuario)
                                        .setMessage(message)
                                        .build();

                                SendMessageResponse response = stub.sendMessage(sendRequest);
                            }
                        }
                    } catch (StatusRuntimeException e) {
                        if (e.getStatus().getCode() == io.grpc.Status.Code.UNAVAILABLE) {
                            isRunning[0] = false;
                            currentThread.interrupt(); // Interrumpir el hilo principal
                            break;
                        } else {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            });

            // Hilo para recibir mensajes
            Thread receiveThread = new Thread(() -> {
                long lastTimeStamp = Instant.now().getEpochSecond();
                while (isRunning[0]) {
                    try {
                        GetMessagesRequest request = GetMessagesRequest.newBuilder()
                                .setLastMessageTimestamp(lastTimeStamp)
                                .setUser(usuario)
                                .build();

                        GetMessagesResponse response = stub.getMessages(request);
                        if (!response.getNewMessagesList().isEmpty()) {
                            for (Message msg : response.getNewMessagesList()) {
                                System.out.println(formatearTimeStamp(msg.getTimeStamp()) + msg.getUser().getName() + ": " + msg.getContent());
                                lastTimeStamp = msg.getTimeStamp();
                            }
                        }

                        Thread.sleep(5000);
                    } catch (StatusRuntimeException e) {
                        if (e.getStatus().getCode() == io.grpc.Status.Code.UNAVAILABLE) {
                            isRunning[0] = false; // Detener el hilo de recepción
                            currentThread.interrupt(); // Interrumpir el hilo principal
                            break;
                        } else {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("El hilo de recepción de mensajes fue interrumpido.");
                        break;
                    }
                }
            });

            // Iniciar ambos hilos
            sendThread.start();
            receiveThread.start();

            // Unir hilos para que el programa no termine hasta que ambos hilos finalicen
            try {
                sendThread.join();
                receiveThread.join();
            } catch (InterruptedException e) {
                System.out.println("La ejecución fue interrumpida.");
                Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
            }

        } catch (StatusRuntimeException e) {
            System.err.println("Servidor no disponible. Verifique que el servidor esté en ejecución.");
        } finally {
            // Cerrar el canal al finalizar
            channel.shutdownNow();
            System.exit(0);
        }
    }
}
