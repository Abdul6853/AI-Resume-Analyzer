package com.example.ResumeAnalyxer.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {
    private final ChatClient chatClient;

    public GeminiService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String prompt){
       return chatClient.prompt(prompt).call().content();
    }
}
