package com.example.chat;

import app.pdytr.ChatServiceGrpc;
import app.pdytr.ChatServiceProto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ChatClientEj4 {

    public static void main(String[] args) {
        Thread client1 = new Thread(new ChatClientThread("Joaco27", "Hola, soy Joaco27"));
        Thread client2 = new Thread(new ChatClientThread("Rubianito", "Hola, soy Rubianito"));
        Thread client3 = new Thread(new ChatClientThread("Rafafafa", "Hola, soy Rafafafa"));
        Thread client4 = new Thread(new ChatClientThread("Juanarda", "Hola, soy Juanarda"));
        Thread client5 = new Thread(new ChatClientThread("Ziplocata", "Hola, soy Ziplocata"));


        client1.start();
        client2.start();
        client3.start();
        client4.start();
        client5.start();

        try {
            client1.join();
            client2.join();
            client3.join();
            client4.join();
            client5.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
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
        final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        final ChatServiceGrpc.ChatServiceBlockingStub stub = ChatServiceGrpc.newBlockingStub(channel);

        User user = User.newBuilder()
                .setName(username)
                .build();

        ConnectRequest connectRequest = ConnectRequest.newBuilder()
                .setUser(user)
                .build();

        ConnectResponse connectResponse = stub.connect(connectRequest);
        User usuario = connectResponse.getUser();
        //System.out.println(connectResponse.getWelcome());

        Message message = Message.newBuilder()
                .setUser(usuario)
                .setContent(messageText)
                .build();

        SendMessageRequest sendRequest = SendMessageRequest.newBuilder()
                .setUser(usuario)
                .setMessage(message)
                .build();

        SendMessageResponse response = stub.sendMessage(sendRequest);

        DisconnectRequest disconnectRequest = DisconnectRequest.newBuilder()
                .setUser(usuario)
                .build();
        DisconnectResponse disconnectResponse = stub.disconnect(disconnectRequest);
        //System.out.println(disconnectResponse.getGoodbye());

        channel.shutdownNow();
    }
}
