package br.com.aluraFlix.controllerTest;


import br.com.aluraFlix.domain.Categorias;
import br.com.aluraFlix.videos.VideosForm;
import br.com.aluraFlix.videos.VideosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VideoControllerTest {

    static String POST_VIDEOS = "/videos";

    @MockBean
    VideosService videosService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /videoSalvo")
    void deveriaSalvarVideo() throws Exception {
        // Cenario
        BDDMockito.given(videosService.salvarVideo(Mockito.any(VideosForm.class)))
                .willReturn("Video salvo com sucesso.");
        VideosForm videosForm = criarVideo();

        String json = new ObjectMapper().writeValueAsString(videosForm);

        // Simulando o corpo para requisicao post
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(POST_VIDEOS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        // Simulando chamada
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();

        // Retorno esperado
        Assertions.assertEquals("Video salvo com sucesso.", mvcResult.getResponse().getContentAsString());

    }
    private VideosForm criarVideo() {
        return new VideosForm("teste",
                "teste",
                "teste",
                new Categorias("teste", "teste"));
    }
}
