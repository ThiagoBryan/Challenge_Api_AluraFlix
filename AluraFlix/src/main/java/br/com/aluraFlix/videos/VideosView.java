package br.com.aluraFlix.videos;

import br.com.aluraFlix.domain.Videos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideosView {

    private Long id;
    private String titulo;
    private String descricao;
    private String url;


}
