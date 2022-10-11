package videos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VideosView {

    private Long id;
    private String titulo;
    private String descricao;
    private String url;
}
