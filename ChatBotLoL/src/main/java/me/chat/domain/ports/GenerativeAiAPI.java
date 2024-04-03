package me.chat.domain.ports;

public interface GenerativeAiAPI {
    String generateContent(String objective, String context);
}
