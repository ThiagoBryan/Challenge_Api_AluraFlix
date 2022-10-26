package br.com.aluraFlix.controllerTest;


import br.com.aluraFlix.categorias.CategoriasRepository;
import br.com.aluraFlix.domain.Categorias;
import br.com.aluraFlix.videos.VideosForm;
import br.com.aluraFlix.videos.VideosRepository;
import br.com.aluraFlix.videos.VideosService;
import br.com.aluraFlix.videos.VideosView;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VideoControllerTest {

    static String VIDEOS = "/videos";
    static String GET_TODOS_VIDEOS = "/videos/todos";

    @MockBean
    VideosService videosService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    VideosRepository videosRepository;

    @Autowired
    CategoriasRepository categoriasRepository;

    @Test
    @DisplayName("POST /videoSalvo")
    public void deveriaSalvarVideo() throws Exception {
        // Cenario
        BDDMockito.given(videosService.salvarVideo(Mockito.any(VideosForm.class)))
                .willReturn("Video salvo com sucesso.");
        VideosForm videosForm = criarVideo();

        String json = new ObjectMapper().writeValueAsString(videosForm);

        // Simulando o corpo para requisicao post
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(VIDEOS)
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

//    @Test
//    @DisplayName("GET /todosVideos")
//    public void deveriaRetornarTodosOsVideos() throws Exception {
//
//        List<VideosView> videosViews = retonarListaVideos();
////
////        BDDMockito.given(videosService.todosVideos())
////                .willReturn(videosViews);
//
////        ResultActions resultAction = mockMvc
////                .perform(get(GET_TODOS_VIDEOS)
////                        .accept(MediaType.APPLICATION_JSON));
////
//        Categorias categoria = new Categorias("teste", "teste");
//        categoriasRepository.save(categoria);
//
//        Videos videos = new Videos("teste","teste","teste", categoria);
//        videosRepository.save(videos);
//
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
//                .get(GET_TODOS_VIDEOS)
//                .accept(MediaType.APPLICATION_JSON);
//
//        // Simulando chamada
//        mockMvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content[0].id").value(videosViews.get(0).getId()))
//                .andExpect(jsonPath("$.content[0].titulo").value(videosViews.get(0).getTitulo()))
//                .andExpect(jsonPath("$.content[0].descricao").value(videosViews.get(0).getDescricao()))
//                .andExpect(jsonPath("$.content[0].url").value(videosViews.get(0).getUrl()))
//                .andExpect(jsonPath("$.content[0].categoria.id").value(videosViews.get(0).getCategoria().getId()))
//                .andExpect(jsonPath("$.content[0].categoria.titulo").value(videosViews.get(0).getCategoria().getTitulo()))
//                .andExpect(jsonPath("$.content[0].categoria.cor").value(videosViews.get(0).getCategoria().getCor()));
//
//    }

    @Test
    @DisplayName("GET /videoId")
    public void deveriaRetornarVideoComId() throws Exception {

        VideosView videosView = retonarVideo();

        Long videoId = 1L;
        BDDMockito.given(videosService.mostrarVideoId(videoId))
                .willReturn(videosView);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(VIDEOS.concat("/" + 1))
                .accept(MediaType.APPLICATION_JSON);

//        MvcResult mvcResult = mockMvc.perform(request)
//                .andExpect(status().isOk())
//                .andReturn();
//
//        Assertions.assertEquals(videosView, mvcResult.getResponse().getContentAsString());

        mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1l))
                .andExpect(jsonPath("titulo").value(videosView.getTitulo()))
                .andExpect(jsonPath("descricao").value(videosView.getDescricao()))
                .andExpect(jsonPath("url").value(videosView.getUrl()))
                .andExpect(jsonPath("categoria.titulo").value(videosView.getCategoria().getTitulo()))
                .andExpect(jsonPath("categoria.cor").value(videosView.getCategoria().getCor()));
    }




    private VideosForm criarVideo() {
        return new VideosForm("teste",
                "teste",
                "teste",
                new Categorias("teste", "teste"));
    }

    private VideosView retonarVideo() {
        return new VideosView(1L,
                "teste",
                "teste",
                "teste",
                new Categorias("teste", "teste"));
    }

    private List<VideosView> retonarListaVideos() {
        List<VideosView> videos = new ArrayList<>();
        Categorias categorias = new Categorias("teste", "teste");
        categorias.setId(1L);
        VideosView view = new VideosView(1L,
                "teste",
                "teste",
                "teste",
                categorias);
        videos.add(view);

        return videos;
    }

}
