package com.example.chat;
import app.pdytr.Punto5ServiceGrpc;
import app.pdytr.Punto5ServiceProto.*;
import io.grpc.stub.StreamObserver;

public class Punto5ServiceImpl extends Punto5ServiceGrpc.Punto5ServiceImplBase {

    @Override
    public void measure(MensaggeRequest request, StreamObserver<MensaggeResponse> responseObserver) {
        // Implementación del servicio: responde con "OK"
        MensaggeResponse response = MensaggeResponse.newBuilder()
                .setResult("OK")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
    