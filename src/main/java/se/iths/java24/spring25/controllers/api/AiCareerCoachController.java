package se.iths.java24.spring25.controllers.api;

import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai/career-coach")
public class AiCareerCoachController {

    private final MistralAiChatModel chatModel;

    public AiCareerCoachController(MistralAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @PostMapping
    @Cacheable("careerCoachCache")
    public String chatWithAi(@RequestBody String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            return "Please enter a valid question.";
        }

        String systemPrompt = """
            You are an AI career coach for a platform called WorkConnect.
            Your job is to give short, clear and practical career advice, tailored to young professionals.
            Always keep the answer under 100 words.
            User input: %s
            """.formatted(userInput.trim());

        return chatModel.call(systemPrompt);
    }
}
