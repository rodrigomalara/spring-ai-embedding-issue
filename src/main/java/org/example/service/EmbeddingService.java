package org.example.service;

import lombok.AllArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vertexai.embedding.text.VertexAiTextEmbeddingModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmbeddingService {
    protected final TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();

    private final VertexAiTextEmbeddingModel vertexAiEmbeddingModel;

    public void embed() {
        var embeddings = new ArrayList<float[]>();
        for (int i = 0; i < 50; i++) {
            String question = QuestionGenerator.generateQuestion(i);
            var document = new Document(question);
            var documents = tokenTextSplitter.apply(List.of(document));

            documents.forEach(doc -> embeddings.add(vertexAiEmbeddingModel.embed(doc.getText())));
        }

        embeddings.forEach(System.out::println);
    }


}
