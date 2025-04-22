package se.iths.java24.spring25.controllers.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.security.oauth2.core.user.OAuth2User;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AiCareerCoachControllerTest {

    @Mock
    private MistralAiChatModel chatModel;

    @InjectMocks
    private AiCareerCoachController aiCareerCoachController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void chatWithAiShouldReturnErrorMessageWhenPromptIsTooShort() {
        String prompt = "Hi";
        OAuth2User user = mock(OAuth2User.class);

        String response = aiCareerCoachController.chatWithAi(prompt, user);
        assertEquals("Please provide a more specific career question.", response);
    }
}
