package com.example.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.7.0)",
    comments = "Source: menssage.proto")
public final class Punto5ServiceGrpc {

  private Punto5ServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.grpc.Punto5Service";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.grpc.MenssageProto.MenssageRequest,
      com.example.grpc.MenssageProto.MenssageResponse> METHOD_MEASURE =
      io.grpc.MethodDescriptor.<com.example.grpc.MenssageProto.MenssageRequest, com.example.grpc.MenssageProto.MenssageResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.example.grpc.Punto5Service", "Measure"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.grpc.MenssageProto.MenssageRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.grpc.MenssageProto.MenssageResponse.getDefaultInstance()))
          .setSchemaDescriptor(new Punto5ServiceMethodDescriptorSupplier("Measure"))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static Punto5ServiceStub newStub(io.grpc.Channel channel) {
    return new Punto5ServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static Punto5ServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new Punto5ServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static Punto5ServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new Punto5ServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class Punto5ServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void measure(com.example.grpc.MenssageProto.MenssageRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.MenssageProto.MenssageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_MEASURE, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_MEASURE,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.MenssageProto.MenssageRequest,
                com.example.grpc.MenssageProto.MenssageResponse>(
                  this, METHODID_MEASURE)))
          .build();
    }
  }

  /**
   */
  public static final class Punto5ServiceStub extends io.grpc.stub.AbstractStub<Punto5ServiceStub> {
    private Punto5ServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private Punto5ServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Punto5ServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new Punto5ServiceStub(channel, callOptions);
    }

    /**
     */
    public void measure(com.example.grpc.MenssageProto.MenssageRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.MenssageProto.MenssageResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_MEASURE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class Punto5ServiceBlockingStub extends io.grpc.stub.AbstractStub<Punto5ServiceBlockingStub> {
    private Punto5ServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private Punto5ServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Punto5ServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new Punto5ServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.MenssageProto.MenssageResponse measure(com.example.grpc.MenssageProto.MenssageRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_MEASURE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class Punto5ServiceFutureStub extends io.grpc.stub.AbstractStub<Punto5ServiceFutureStub> {
    private Punto5ServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private Punto5ServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Punto5ServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new Punto5ServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.MenssageProto.MenssageResponse> measure(
        com.example.grpc.MenssageProto.MenssageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_MEASURE, getCallOptions()), request);
    }
  }

  private static final int METHODID_MEASURE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final Punto5ServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(Punto5ServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MEASURE:
          serviceImpl.measure((com.example.grpc.MenssageProto.MenssageRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.MenssageProto.MenssageResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class Punto5ServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    Punto5ServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpc.MenssageProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Punto5Service");
    }
  }

  private static final class Punto5ServiceFileDescriptorSupplier
      extends Punto5ServiceBaseDescriptorSupplier {
    Punto5ServiceFileDescriptorSupplier() {}
  }

  private static final class Punto5ServiceMethodDescriptorSupplier
      extends Punto5ServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    Punto5ServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (Punto5ServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new Punto5ServiceFileDescriptorSupplier())
              .addMethod(METHOD_MEASURE)
              .build();
        }
      }
    }
    return result;
  }
}
