# Spring AI VertexAiTextEmbeddingModel embedding issue

# How to reproduce the issue

Create a Google Cloud account and a service account that can use the Vertex AI API.

Create the file  `vertex-ai-credentials.json` in the src/main/resources directory like in the example below:

```json
{
  "account": "",
  "client_id": "XXXXXXX.apps.googleusercontent.com",
  "client_secret": "XXXXXXX",
  "quota_project_id": "XXXX",
  "refresh_token": "XXXXXX",
  "type": "authorized_user",
  "universe_domain": "googleapis.com"
}
```

Run this Spring Boot app. It will start embedding right after it is started.

Then it will throw the exception below around the 35th embedding

```text
2025-03-31T17:10:25.670-03:00 ERROR 65030 --- [embedding-proxy-service] [           main] i.g.i.ManagedChannelOrphanWrapper        : *~*~*~ Previous channel ManagedChannelImpl{logId=33, target=us-central1-aiplatform.googleapis.com:443} was garbage collected without being shut down! ~*~*~*
    Make sure to call shutdown()/shutdownNow()

java.lang.RuntimeException: ManagedChannel allocation site
	at io.grpc.internal.ManagedChannelOrphanWrapper$ManagedChannelReference.<init>(ManagedChannelOrphanWrapper.java:102) ~[grpc-core-1.69.0.jar:1.69.0]
	at io.grpc.internal.ManagedChannelOrphanWrapper.<init>(ManagedChannelOrphanWrapper.java:60) ~[grpc-core-1.69.0.jar:1.69.0]
	at io.grpc.internal.ManagedChannelOrphanWrapper.<init>(ManagedChannelOrphanWrapper.java:51) ~[grpc-core-1.69.0.jar:1.69.0]
	at io.grpc.internal.ManagedChannelImplBuilder.build(ManagedChannelImplBuilder.java:710) ~[grpc-core-1.69.0.jar:1.69.0]
	at io.grpc.ForwardingChannelBuilder2.build(ForwardingChannelBuilder2.java:272) ~[grpc-api-1.69.0.jar:1.69.0]
	at com.google.api.gax.grpc.InstantiatingGrpcChannelProvider.createSingleChannel(InstantiatingGrpcChannelProvider.java:693) ~[gax-grpc-2.60.0.jar:2.60.0]
	at com.google.api.gax.grpc.ChannelPool.<init>(ChannelPool.java:106) ~[gax-grpc-2.60.0.jar:2.60.0]
	at com.google.api.gax.grpc.ChannelPool.create(ChannelPool.java:84) ~[gax-grpc-2.60.0.jar:2.60.0]
	at com.google.api.gax.grpc.InstantiatingGrpcChannelProvider.createChannel(InstantiatingGrpcChannelProvider.java:319) ~[gax-grpc-2.60.0.jar:2.60.0]
	at com.google.api.gax.grpc.InstantiatingGrpcChannelProvider.getTransportChannel(InstantiatingGrpcChannelProvider.java:312) ~[gax-grpc-2.60.0.jar:2.60.0]
	at com.google.api.gax.rpc.ClientContext.create(ClientContext.java:226) ~[gax-2.60.0.jar:2.60.0]
	at com.google.cloud.aiplatform.v1.stub.GrpcPredictionServiceStub.create(GrpcPredictionServiceStub.java:295) ~[google-cloud-aiplatform-3.52.0.jar:3.52.0]
	at com.google.cloud.aiplatform.v1.stub.PredictionServiceStubSettings.createStub(PredictionServiceStubSettings.java:330) ~[google-cloud-aiplatform-3.52.0.jar:3.52.0]
	at com.google.cloud.aiplatform.v1.PredictionServiceClient.<init>(PredictionServiceClient.java:412) ~[google-cloud-aiplatform-3.52.0.jar:3.52.0]
	at com.google.cloud.aiplatform.v1.PredictionServiceClient.create(PredictionServiceClient.java:394) ~[google-cloud-aiplatform-3.52.0.jar:3.52.0]
	at org.springframework.ai.vertexai.embedding.text.VertexAiTextEmbeddingModel.createPredictionServiceClient(VertexAiTextEmbeddingModel.java:209) ~[spring-ai-vertex-ai-embedding-1.0.0-M6.jar:1.0.0-M6]
	at org.springframework.ai.vertexai.embedding.text.VertexAiTextEmbeddingModel.lambda$call$2(VertexAiTextEmbeddingModel.java:131) ~[spring-ai-vertex-ai-embedding-1.0.0-M6.jar:1.0.0-M6]
	at io.micrometer.observation.Observation.observe(Observation.java:565) ~[micrometer-observation-1.13.3.jar:1.13.3]
	at org.springframework.ai.vertexai.embedding.text.VertexAiTextEmbeddingModel.call(VertexAiTextEmbeddingModel.java:130) ~[spring-ai-vertex-ai-embedding-1.0.0-M6.jar:1.0.0-M6]
	at org.springframework.ai.embedding.EmbeddingModel.embed(EmbeddingModel.java:67) ~[spring-ai-core-1.0.0-M6.jar:1.0.0-M6]
	at org.springframework.ai.embedding.EmbeddingModel.embed(EmbeddingModel.java:49) ~[spring-ai-core-1.0.0-M6.jar:1.0.0-M6]
	at org.example.service.EmbeddingService.lambda$onApplicationEvent$0(EmbeddingService.java:31) ~[classes/:na]
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596) ~[na:na]
	at org.example.service.EmbeddingService.onApplicationEvent(EmbeddingService.java:31) ~[classes/:na]
	at org.example.service.EmbeddingService.onApplicationEvent(EmbeddingService.java:15) ~[classes/:na]
	at org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener(SimpleApplicationEventMulticaster.java:185) ~[spring-context-6.1.12.jar:6.1.12]
	at org.springframework.context.event.SimpleApplicationEventMulticaster.invokeListener(SimpleApplicationEventMulticaster.java:178) ~[spring-context-6.1.12.jar:6.1.12]
	at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:156) ~[spring-context-6.1.12.jar:6.1.12]
	at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:452) ~[spring-context-6.1.12.jar:6.1.12]
	at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:385) ~[spring-context-6.1.12.jar:6.1.12]
	at org.springframework.boot.context.event.EventPublishingRunListener.ready(EventPublishingRunListener.java:109) ~[spring-boot-3.3.3.jar:3.3.3]
	at org.springframework.boot.SpringApplicationRunListeners.lambda$ready$6(SpringApplicationRunListeners.java:80) ~[spring-boot-3.3.3.jar:3.3.3]
	at java.base/java.lang.Iterable.forEach(Iterable.java:75) ~[na:na]
	at org.springframework.boot.SpringApplicationRunListeners.doWithListeners(SpringApplicationRunListeners.java:118) ~[spring-boot-3.3.3.jar:3.3.3]
	at org.springframework.boot.SpringApplicationRunListeners.doWithListeners(SpringApplicationRunListeners.java:112) ~[spring-boot-3.3.3.jar:3.3.3]
	at org.springframework.boot.SpringApplicationRunListeners.ready(SpringApplicationRunListeners.java:80) ~[spring-boot-3.3.3.jar:3.3.3]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:349) ~[spring-boot-3.3.3.jar:3.3.3]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1363) ~[spring-boot-3.3.3.jar:3.3.3]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1352) ~[spring-boot-3.3.3.jar:3.3.3]
	at org.example.ExampleApplication.main(ExampleApplication.java:9) ~[classes/:na]

```