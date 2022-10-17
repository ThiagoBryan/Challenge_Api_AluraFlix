package br.com.aluraFlix.videos;

import br.com.aluraFlix.domain.Categorias;
import br.com.aluraFlix.domain.Videos;
import lombok.*;

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



}
