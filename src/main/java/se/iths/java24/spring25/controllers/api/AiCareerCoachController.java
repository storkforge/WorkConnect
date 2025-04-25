package se.iths.java24.spring25.controllers.api;

import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai/career-coach")
public class AiCareerCoachController {

    private final MistralAiChatModel chatModel;

    public AiCareerCoachController(MistralAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @PostMapping
    @Cacheable(
            value = "careerCoachCache",
            key = "T(java.util.Objects).hash(#prompt, #principal?.getAttribute('email'))"
    )
    public String chatWithAi(@RequestBody String prompt, @AuthenticationPrincipal OAuth2User principal) {

        if (prompt == null || prompt.trim().length() < 5) {
            return "Please provide a more specific career question.";
        }


        String systemPrompt = """
                You are an AI Career Coach helping young professionals find their career path.
                Provide short, concise and friendly advice based on their question.
                Focus on job fields, skills, education paths or internships when relevant.
                Do not exceed 3 sentences.
                """;

        String fullPrompt = systemPrompt + "\n\nUser question: " + prompt.trim();

        return chatModel.call(fullPrompt);
    }
}
