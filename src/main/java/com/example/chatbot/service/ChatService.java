package com.example.chatbot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.chatbot.model.ChatResponse;

@Service
public class ChatService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final WebClient webClient;

    public ChatService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public ChatResponse getChatResponse(String message) {
        try {
            String response = getHardcodedResponse(message);
            if (response != null) {
                return new ChatResponse(response);
            }

            Map<String, Object> requestBody = new HashMap<>();
            Map<String, Object> content = new HashMap<>();
            Map<String, Object> part = new HashMap<>();

            part.put("text", message);
            content.put("parts", new Object[] { part });
            requestBody.put("contents", new Object[] { content });

            String fullUrl = apiUrl + "?key=" + apiKey;

            Map<String, Object> apiResponse = webClient.post()
                    .uri(fullUrl)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            String responseText = extractResponseText(apiResponse);
            return new ChatResponse(responseText != null ? responseText : "Sorry, I couldn't generate a response.");

        } catch (Exception e) {
            e.printStackTrace();
            return new ChatResponse("I'm currently working offline with limited knowledge. Please try asking about DevOps, Maven, or Spring Boot.");
        }
    }

    private String getHardcodedResponse(String message) {
        if (message == null) return null;
        String lowerMessage = message.toLowerCase().trim();

        if (lowerMessage.equals("hello") || lowerMessage.equals("hi") || lowerMessage.equals("hey")) {
            return "Hello! 👋 Welcome to the Web-Based Chatbot. How can I help you today?";
        }

        if (lowerMessage.contains("devops")) {
            return "DevOps blends development and operations to enable faster, reliable software delivery.";
        }

        if (lowerMessage.contains("maven")) {
            return "Maven is a build automation and dependency management tool for Java projects.";
        }

        if (lowerMessage.contains("spring boot")) {
            return "Spring Boot simplifies building production-ready Spring applications with auto-configuration and embedded servers.";
        }

        if (lowerMessage.contains("thank")) {
            return "You're welcome! 😊";
        }

        return null;
    }

    private String extractResponseText(Map<String, Object> response) {
        try {
            if (response == null) return null;
            Object candidatesObj = response.get("candidates");
            if (candidatesObj instanceof List) {
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> candidates = (List<Map<String, Object>>) candidatesObj;
                if (!candidates.isEmpty()) {
                    Map<String, Object> candidate = candidates.get(0);
                    Object contentObj = candidate.get("content");
                    if (contentObj instanceof Map) {
                        @SuppressWarnings("unchecked")
                        Map<String, Object> content = (Map<String, Object>) contentObj;
                        Object partsObj = content.get("parts");
                        if (partsObj instanceof List) {
                            @SuppressWarnings("unchecked")
                            List<Map<String, Object>> parts = (List<Map<String, Object>>) partsObj;
                            if (!parts.isEmpty()) {
                                Map<String, Object> part = parts.get(0);
                                return (String) part.get("text");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
