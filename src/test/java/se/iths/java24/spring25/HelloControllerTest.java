package se.iths.java24.spring25;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.java24.spring25.domain.PlaygroundService;
import se.iths.java24.spring25.domain.entity.Playground;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @MockitoBean
    PlaygroundService playgroundService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getPlaygrounds() throws Exception {
        var playground = new Playground();
        playground.setName("test");
        playground.setId(1);
        when(playgroundService.getAllPlaygrounds()).thenReturn(List.of(playground));

        mockMvc.perform(get("/playgrounds"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("test"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()", Matchers.is(1)));
    }


}
