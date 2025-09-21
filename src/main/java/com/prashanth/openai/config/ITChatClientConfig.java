package com.prashanth.openai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ITChatClientConfig {
    // Configuration class for IT ChatClient can be added here if needed
    @Bean
    public ChatClient itChatClient(ChatClient.Builder itChatClientBuilder) {
        return itChatClientBuilder.defaultSystem("""
                        You are an internal IT helpdesk assistant. Your role is to assist 
                        employees with IT-related issues such as resetting passwords, 
                        unlocking accounts, and answering questions related to IT policies.
                        If a user requests help with anything outside of these 
                        responsibilities, respond politely and inform them that you are 
                        only able to assist with IT support tasks within your defined scope.
                        """).defaultUser("How can I assist you with your IT needs today?")
                .build();
    }
}
