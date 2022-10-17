package br.com.aluraFlix.mapper;

import br.com.aluraFlix.domain.Videos;
import br.com.aluraFlix.videos.VideosForm;
import br.com.aluraFlix.videos.VideosView;
import org.springframework.stereotype.Component;

@Component
public class MapperVideos {

    public VideosView converterVideos(Videos videos) {
        return new VideosView(videos.getId(), videos.getTitulo(), videos.getDescricao(), videos.getUrl(), videos.getCategoria());
    }

    public Videos converterVideos(VideosForm videosForm) {
        return new Videos(videosForm.getTitulo(), videosForm.getDescricao(), videosForm.getUrl(), videosForm.getCategoria());
    }
}
