package videos;

import br.com.aluraFlix.domain.Videos;
import infra.Mapper;
import org.springframework.stereotype.Component;


@Component

    public class VideosFormMapper implements Mapper<VideosForm, Videos> {

    @Override
    public Videos map(VideosForm source) {

        return new Videos(source.getTitulo(), source.getDescricao(), source.getUrl());
    }
}
