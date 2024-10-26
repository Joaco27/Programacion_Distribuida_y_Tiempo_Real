package com.example.chat;
import com.example.chat.ChatServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws IOException, InterruptedException {
        try (FileWriter fileWriter = new FileWriter("Historial.txt", false)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        Server server = ServerBuilder.forPort(8080)
                .addService(new ChatServiceImpl())
                .build();

        System.out.println("Starting server...");
        server.start();
        System.out.println("Server started on port 8080");

        server.awaitTermination();
    }
}
