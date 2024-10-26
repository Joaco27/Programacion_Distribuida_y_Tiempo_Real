package app.pdytr;

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
    comments = "Source: chat_service.proto")
public final class ChatServiceGrpc {

  private ChatServiceGrpc() {}

  public static final String SERVICE_NAME = "app.pdytr.ChatService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<app.pdytr.ChatServiceProto.ConnectRequest,
      app.pdytr.ChatServiceProto.ConnectResponse> METHOD_CONNECT =
      io.grpc.MethodDescriptor.<app.pdytr.ChatServiceProto.ConnectRequest, app.pdytr.ChatServiceProto.ConnectResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "app.pdytr.ChatService", "Connect"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              app.pdytr.ChatServiceProto.ConnectRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              app.pdytr.ChatServiceProto.ConnectResponse.getDefaultInstance()))
          .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("Connect"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<app.pdytr.ChatServiceProto.DisconnectRequest,
      app.pdytr.ChatServiceProto.DisconnectResponse> METHOD_DISCONNECT =
      io.grpc.MethodDescriptor.<app.pdytr.ChatServiceProto.DisconnectRequest, app.pdytr.ChatServiceProto.DisconnectResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "app.pdytr.ChatService", "Disconnect"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              app.pdytr.ChatServiceProto.DisconnectRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              app.pdytr.ChatServiceProto.DisconnectResponse.getDefaultInstance()))
          .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("Disconnect"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<app.pdytr.ChatServiceProto.HistoryRequest,
      app.pdytr.ChatServiceProto.HistoryResponse> METHOD_HISTORY =
      io.grpc.MethodDescriptor.<app.pdytr.ChatServiceProto.HistoryRequest, app.pdytr.ChatServiceProto.HistoryResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "app.pdytr.ChatService", "History"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              app.pdytr.ChatServiceProto.HistoryRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              app.pdytr.ChatServiceProto.HistoryResponse.getDefaultInstance()))
          .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("History"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<app.pdytr.ChatServiceProto.SendMessageRequest,
      app.pdytr.ChatServiceProto.SendMessageResponse> METHOD_SEND_MESSAGE =
      io.grpc.MethodDescriptor.<app.pdytr.ChatServiceProto.SendMessageRequest, app.pdytr.ChatServiceProto.SendMessageResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "app.pdytr.ChatService", "SendMessage"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              app.pdytr.ChatServiceProto.SendMessageRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              app.pdytr.ChatServiceProto.SendMessageResponse.getDefaultInstance()))
          .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("SendMessage"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<app.pdytr.ChatServiceProto.GetMessagesRequest,
      app.pdytr.ChatServiceProto.GetMessagesResponse> METHOD_GET_MESSAGES =
      io.grpc.MethodDescriptor.<app.pdytr.ChatServiceProto.GetMessagesRequest, app.pdytr.ChatServiceProto.GetMessagesResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "app.pdytr.ChatService", "GetMessages"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              app.pdytr.ChatServiceProto.GetMessagesRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              app.pdytr.ChatServiceProto.GetMessagesResponse.getDefaultInstance()))
          .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("GetMessages"))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ChatServiceStub newStub(io.grpc.Channel channel) {
    return new ChatServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ChatServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ChatServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ChatServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ChatServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ChatServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void connect(app.pdytr.ChatServiceProto.ConnectRequest request,
        io.grpc.stub.StreamObserver<app.pdytr.ChatServiceProto.ConnectResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CONNECT, responseObserver);
    }

    /**
     */
    public void disconnect(app.pdytr.ChatServiceProto.DisconnectRequest request,
        io.grpc.stub.StreamObserver<app.pdytr.ChatServiceProto.DisconnectResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DISCONNECT, responseObserver);
    }

    /**
     */
    public void history(app.pdytr.ChatServiceProto.HistoryRequest request,
        io.grpc.stub.StreamObserver<app.pdytr.ChatServiceProto.HistoryResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_HISTORY, responseObserver);
    }

    /**
     */
    public void sendMessage(app.pdytr.ChatServiceProto.SendMessageRequest request,
        io.grpc.stub.StreamObserver<app.pdytr.ChatServiceProto.SendMessageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SEND_MESSAGE, responseObserver);
    }

    /**
     */
    public void getMessages(app.pdytr.ChatServiceProto.GetMessagesRequest request,
        io.grpc.stub.StreamObserver<app.pdytr.ChatServiceProto.GetMessagesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_MESSAGES, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_CONNECT,
            asyncUnaryCall(
              new MethodHandlers<
                app.pdytr.ChatServiceProto.ConnectRequest,
                app.pdytr.ChatServiceProto.ConnectResponse>(
                  this, METHODID_CONNECT)))
          .addMethod(
            METHOD_DISCONNECT,
            asyncUnaryCall(
              new MethodHandlers<
                app.pdytr.ChatServiceProto.DisconnectRequest,
                app.pdytr.ChatServiceProto.DisconnectResponse>(
                  this, METHODID_DISCONNECT)))
          .addMethod(
            METHOD_HISTORY,
            asyncUnaryCall(
              new MethodHandlers<
                app.pdytr.ChatServiceProto.HistoryRequest,
                app.pdytr.ChatServiceProto.HistoryResponse>(
                  this, METHODID_HISTORY)))
          .addMethod(
            METHOD_SEND_MESSAGE,
            asyncUnaryCall(
              new MethodHandlers<
                app.pdytr.ChatServiceProto.SendMessageRequest,
                app.pdytr.ChatServiceProto.SendMessageResponse>(
                  this, METHODID_SEND_MESSAGE)))
          .addMethod(
            METHOD_GET_MESSAGES,
            asyncUnaryCall(
              new MethodHandlers<
                app.pdytr.ChatServiceProto.GetMessagesRequest,
                app.pdytr.ChatServiceProto.GetMessagesResponse>(
                  this, METHODID_GET_MESSAGES)))
          .build();
    }
  }

  /**
   */
  public static final class ChatServiceStub extends io.grpc.stub.AbstractStub<ChatServiceStub> {
    private ChatServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceStub(channel, callOptions);
    }

    /**
     */
    public void connect(app.pdytr.ChatServiceProto.ConnectRequest request,
        io.grpc.stub.StreamObserver<app.pdytr.ChatServiceProto.ConnectResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CONNECT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void disconnect(app.pdytr.ChatServiceProto.DisconnectRequest request,
        io.grpc.stub.StreamObserver<app.pdytr.ChatServiceProto.DisconnectResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DISCONNECT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void history(app.pdytr.ChatServiceProto.HistoryRequest request,
        io.grpc.stub.StreamObserver<app.pdytr.ChatServiceProto.HistoryResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_HISTORY, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendMessage(app.pdytr.ChatServiceProto.SendMessageRequest request,
        io.grpc.stub.StreamObserver<app.pdytr.ChatServiceProto.SendMessageResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SEND_MESSAGE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getMessages(app.pdytr.ChatServiceProto.GetMessagesRequest request,
        io.grpc.stub.StreamObserver<app.pdytr.ChatServiceProto.GetMessagesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_MESSAGES, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ChatServiceBlockingStub extends io.grpc.stub.AbstractStub<ChatServiceBlockingStub> {
    private ChatServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public app.pdytr.ChatServiceProto.ConnectResponse connect(app.pdytr.ChatServiceProto.ConnectRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CONNECT, getCallOptions(), request);
    }

    /**
     */
    public app.pdytr.ChatServiceProto.DisconnectResponse disconnect(app.pdytr.ChatServiceProto.DisconnectRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DISCONNECT, getCallOptions(), request);
    }

    /**
     */
    public app.pdytr.ChatServiceProto.HistoryResponse history(app.pdytr.ChatServiceProto.HistoryRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_HISTORY, getCallOptions(), request);
    }

    /**
     */
    public app.pdytr.ChatServiceProto.SendMessageResponse sendMessage(app.pdytr.ChatServiceProto.SendMessageRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SEND_MESSAGE, getCallOptions(), request);
    }

    /**
     */
    public app.pdytr.ChatServiceProto.GetMessagesResponse getMessages(app.pdytr.ChatServiceProto.GetMessagesRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_MESSAGES, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ChatServiceFutureStub extends io.grpc.stub.AbstractStub<ChatServiceFutureStub> {
    private ChatServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<app.pdytr.ChatServiceProto.ConnectResponse> connect(
        app.pdytr.ChatServiceProto.ConnectRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CONNECT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<app.pdytr.ChatServiceProto.DisconnectResponse> disconnect(
        app.pdytr.ChatServiceProto.DisconnectRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DISCONNECT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<app.pdytr.ChatServiceProto.HistoryResponse> history(
        app.pdytr.ChatServiceProto.HistoryRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_HISTORY, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<app.pdytr.ChatServiceProto.SendMessageResponse> sendMessage(
        app.pdytr.ChatServiceProto.SendMessageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SEND_MESSAGE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<app.pdytr.ChatServiceProto.GetMessagesResponse> getMessages(
        app.pdytr.ChatServiceProto.GetMessagesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_MESSAGES, getCallOptions()), request);
    }
  }

  private static final int METHODID_CONNECT = 0;
  private static final int METHODID_DISCONNECT = 1;
  private static final int METHODID_HISTORY = 2;
  private static final int METHODID_SEND_MESSAGE = 3;
  private static final int METHODID_GET_MESSAGES = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ChatServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ChatServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CONNECT:
          serviceImpl.connect((app.pdytr.ChatServiceProto.ConnectRequest) request,
              (io.grpc.stub.StreamObserver<app.pdytr.ChatServiceProto.ConnectResponse>) responseObserver);
          break;
        case METHODID_DISCONNECT:
          serviceImpl.disconnect((app.pdytr.ChatServiceProto.DisconnectRequest) request,
              (io.grpc.stub.StreamObserver<app.pdytr.ChatServiceProto.DisconnectResponse>) responseObserver);
          break;
        case METHODID_HISTORY:
          serviceImpl.history((app.pdytr.ChatServiceProto.HistoryRequest) request,
              (io.grpc.stub.StreamObserver<app.pdytr.ChatServiceProto.HistoryResponse>) responseObserver);
          break;
        case METHODID_SEND_MESSAGE:
          serviceImpl.sendMessage((app.pdytr.ChatServiceProto.SendMessageRequest) request,
              (io.grpc.stub.StreamObserver<app.pdytr.ChatServiceProto.SendMessageResponse>) responseObserver);
          break;
        case METHODID_GET_MESSAGES:
          serviceImpl.getMessages((app.pdytr.ChatServiceProto.GetMessagesRequest) request,
              (io.grpc.stub.StreamObserver<app.pdytr.ChatServiceProto.GetMessagesResponse>) responseObserver);
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

  private static abstract class ChatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ChatServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return app.pdytr.ChatServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ChatService");
    }
  }

  private static final class ChatServiceFileDescriptorSupplier
      extends ChatServiceBaseDescriptorSupplier {
    ChatServiceFileDescriptorSupplier() {}
  }

  private static final class ChatServiceMethodDescriptorSupplier
      extends ChatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ChatServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ChatServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ChatServiceFileDescriptorSupplier())
              .addMethod(METHOD_CONNECT)
              .addMethod(METHOD_DISCONNECT)
              .addMethod(METHOD_HISTORY)
              .addMethod(METHOD_SEND_MESSAGE)
              .addMethod(METHOD_GET_MESSAGES)
              .build();
        }
      }
    }
    return result;
  }
}
