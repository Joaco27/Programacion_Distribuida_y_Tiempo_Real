package com.example.chat;
import com.google.protobuf.Timestamp;

import app.pdytr.ChatServiceGrpc;
import app.pdytr.ChatServiceProto.*;
import io.grpc.stub.StreamObserver;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;


public class ChatServiceImpl extends ChatServiceGrpc.ChatServiceImplBase {
    private final List<User> userList = new CopyOnWriteArrayList<>();
    private final List<Message> msgList = new CopyOnWriteArrayList<>();
    private int idU = 1;
    private int idM = 1;

    @Override
    public void connect(ConnectRequest request, StreamObserver<ConnectResponse> responseObserver) {
        User user = request.getUser();
        user = user.toBuilder().setId(idU++).build();
        String welcomeMsg = "Bienvenido: " + user.getName();
        userList.add(user);

        String messageText = user.getName() + " se unio al chat";
        Message message = Message.newBuilder()
            .setUser(user)
            .setContent(messageText)
            .setTimeStamp(Instant.now().getEpochSecond())
            .build();
        this.msgList.add(message);
        System.out.println(messageText);

        guardarEnHistorial(messageText);

        ConnectResponse response = ConnectResponse.newBuilder()
            .setWelcome(welcomeMsg).setUser(user).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void disconnect(DisconnectRequest request, StreamObserver<DisconnectResponse> responseObserver) {
        User user = request.getUser();
        userList.removeIf(existingUser -> existingUser.getId() == user.getId());
        String goodbyeMsg = "Hasta pronto: " + user.getName();

        String messageText = user.getName() + " salio del chat";
        Message message = Message.newBuilder()
            .setUser(user)
            .setContent(messageText)
            .setTimeStamp(Instant.now().getEpochSecond())
            .build();
        this.msgList.add(message);
        System.out.println(messageText);

        guardarEnHistorial(messageText);

        DisconnectResponse response = DisconnectResponse.newBuilder()
            .setGoodbye(goodbyeMsg).setUser(user).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void sendMessage(SendMessageRequest request, StreamObserver<SendMessageResponse> responseObserver) {
        User user = request.getUser();
        Message msg = request.getMessage();
        msg = msg.toBuilder().setTimeStamp(Instant.now().getEpochSecond()).build();
        String messageText = this.formatearTimeStamp(msg.getTimeStamp()) + user.getName()+": "+msg.getContent();
        System.out.println(messageText);

        guardarEnHistorial(messageText);

        this.msgList.add(msg);
        SendMessageResponse response = SendMessageResponse.newBuilder().setUser(user).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getMessages(GetMessagesRequest request, StreamObserver<GetMessagesResponse> responseObserver) {
        long lastTimeStamp = request.getLastMessageTimestamp();
        User user = request.getUser();
        List<Message> lista = this.msgAfterLastTimeStamp(lastTimeStamp, user.getId());
        GetMessagesResponse response = GetMessagesResponse.newBuilder()
            .addAllNewMessages(lista).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void history(HistoryRequest request, StreamObserver<HistoryResponse> responseObserver) {
        User user = request.getUser();
        System.out.println("El usuario: " + user.getName() + " solicito el historial");
        HistoryResponse response = HistoryResponse.newBuilder()
            .setMessages(this.generateHistory()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public List<Message> msgAfterLastTimeStamp(long lastTimeStamp, long id){
        return this.msgList.stream()
            .filter(msg -> msg.getTimeStamp() > lastTimeStamp)
            .filter(msg -> msg.getUser().getId() != id)
            .collect(Collectors.toList());
    }

    public String generateHistory(){
        File archivo = new File("Historial.txt");
        StringBuilder contenidoAcumulado = new StringBuilder(); // StringBuilder para eficiencia

        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    contenidoAcumulado.append(linea).append("\n");
                }
            } catch (IOException e) {
                System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo no existe.");
        }

        return contenidoAcumulado.toString();
    }

    public String formatearTimeStamp(long timestamp){
        Instant instant = Instant.ofEpochSecond(timestamp);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = dateTime.format(formatter);
        return "[ " + formattedDate + " ] ";
    }

    public void guardarEnHistorial(String contenido) {
        File archivo = new File("Historial.txt");
        
        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(contenido + "\n"); 
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }

}
