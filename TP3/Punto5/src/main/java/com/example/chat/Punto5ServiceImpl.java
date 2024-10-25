package com.example.chat;

import app.pdytr.MessageProto.*;  
import app.pdytr.Punto5ServiceGrpc;
import io.grpc.stub.StreamObserver;

public class Punto5ServiceImpl extends Punto5ServiceGrpc.Punto5ServiceImplBase {

    @Override
    public void measure(MessageRequest request, StreamObserver<MessageResponse> responseObserver) { 
        MessageResponse response = MessageResponse.newBuilder()
                .setResult("OK")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
