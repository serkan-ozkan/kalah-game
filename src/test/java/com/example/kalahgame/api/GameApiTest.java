package com.example.kalahgame.api;

import com.example.kalahgame.model.Game;
import com.example.kalahgame.model.GameStatus;
import com.example.kalahgame.service.GameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.context.WebApplicationContext;
import javax.annotation.PostConstruct;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class GameApiTest {

    private MockMvc mockMvc;

    @Mock
    private GameService gameService;
    @InjectMocks
    private GameApi gameApi;



    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(gameApi).build();
    }


    @Test
    @DirtiesContext
    public void initGameSuccessfully() throws Exception {
        Game game = new Game();
        Mockito.when(gameService.initGame()).thenReturn(game);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        int i = 0;
    }
}
