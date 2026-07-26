package com.example.ResumeAnalyxer.Controller;

import com.example.ResumeAnalyxer.Service.GeminiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeminiController {
    private final GeminiService geminiService;

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping("gemini/test")
    public String test(){
        return geminiService.chat("hello gemini");
    }
}
