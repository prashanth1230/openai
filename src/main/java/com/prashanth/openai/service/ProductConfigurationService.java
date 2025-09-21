package com.prashanth.openai.service;

import com.prashanth.openai.controller.ProductRecommendationController;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductConfigurationService {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/product-recommendation-template.md")
    private Resource markdownTemplate;

    public ProductConfigurationService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public ProductRecommendationController.ProductRecommendationResponse generateStructuredResponse(String topic, String budget, String category) {
        // Create structured output converter
        var outputConverter = new BeanOutputConverter<>(ProductRecommendationController.ProductRecommendationResponse.class);

        // Create prompt template from markdown file
        PromptTemplate promptTemplate = new PromptTemplate(markdownTemplate);

        // Create variables map
        Map<String, Object> variables = Map.of(
                "topic", topic,
                "budget", budget,
                "category", category,
                "format", outputConverter.getFormat()
        );

        // Generate and return structured response
        return chatClient.prompt()
                .user(promptTemplate.render(variables))
                .call()
                .entity(ProductRecommendationController.ProductRecommendationResponse.class);
    }
}

