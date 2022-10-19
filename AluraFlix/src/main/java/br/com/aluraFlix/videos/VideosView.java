package br.com.aluraFlix.videos;

import br.com.aluraFlix.domain.Categorias;
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



}
