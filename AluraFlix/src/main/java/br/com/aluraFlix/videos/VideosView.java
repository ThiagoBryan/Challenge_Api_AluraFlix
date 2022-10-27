package br.com.aluraFlix.videos;

import br.com.aluraFlix.domain.Categorias;
import br.com.aluraFlix.domain.Videos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VideosView {

    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private Categorias categoria;

    public VideosView(VideosView video, Long idVideo) {
        this.id = video.getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
        this.categoria = video.getCategoria();
    }

    public VideosView(Videos videoView, Long idVideo) {

    }
}
