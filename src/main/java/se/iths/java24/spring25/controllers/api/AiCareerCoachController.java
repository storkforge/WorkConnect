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
    public String chatWithAi(@RequestBody String prompt) {
        return chatModel.call(prompt);
    }

}
