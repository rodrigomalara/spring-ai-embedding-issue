package org.example.config;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.aiplatform.v1.PredictionServiceSettings;
import org.springframework.ai.autoconfigure.vertexai.embedding.VertexAiEmbeddingConnectionProperties;
import org.springframework.ai.vertexai.embedding.VertexAiEmbeddingConnectionDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class VertexAiConfig {

    @Bean
    public GoogleCredentials googleCredentials(VertexAiEmbeddingConnectionProperties connectionProperties) throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(connectionProperties.getCredentialsUri().getInputStream());
        credentials.refreshIfExpired();
        return credentials;
    }

    @Bean
    public VertexAiEmbeddingConnectionDetails connectionDetails(
            VertexAiEmbeddingConnectionProperties connectionProperties,
            GoogleCredentials credentials) throws IOException {

        return VertexAiEmbeddingConnectionDetails.builder()
                .projectId(connectionProperties.getProjectId())
                .location(connectionProperties.getLocation())
                .apiEndpoint(connectionProperties.getApiEndpoint())
                .predictionServiceSettings(
                        PredictionServiceSettings.newBuilder()
                                .setEndpoint(connectionProperties.getApiEndpoint())
                                .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                                .build())
                .build();
    }
}
