package se.iths.java24.spring25.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient chatClient(MistralAiChatModel mistralAiChatModel) {
        return ChatClient.builder(mistralAiChatModel).build();
    }

}
