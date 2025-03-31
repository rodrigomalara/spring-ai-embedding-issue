package org.example.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vertexai.embedding.text.VertexAiTextEmbeddingModel;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class EmbeddingService implements ApplicationListener<ApplicationReadyEvent> {
    protected final TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();

    private final VertexAiTextEmbeddingModel vertexAiEmbeddingModel;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        var embeddings = new ArrayList<float[]>();
        for (int i = 0; i < 50; i++) {
            String question = QuestionGenerator.generateQuestion(i);
            var document = new Document(question);
            var documents = tokenTextSplitter.apply(List.of(document));

            documents.forEach(doc -> embeddings.add(vertexAiEmbeddingModel.embed(doc.getText())));
            log.info("Processed question {} : {}", i, question);
        }
    }
}
