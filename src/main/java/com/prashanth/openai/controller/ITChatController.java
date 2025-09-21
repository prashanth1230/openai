package com.prashanth.openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/it")
public class ITChatController {

    private final ChatClient itChatClient;

    public ITChatController(ChatClient itChatClient) {
        this.itChatClient = itChatClient;
    }

    @GetMapping("/chat")
    public String itChat(@RequestParam("message") String message) {
        return itChatClient
                .prompt()
                .user(message)
                .call().content();
    }
}
