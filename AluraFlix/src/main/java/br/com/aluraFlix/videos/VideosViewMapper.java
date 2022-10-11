package br.com.aluraFlix.videos;

import br.com.aluraFlix.domain.Videos;
import br.com.aluraFlix.infra.Mapper;
import org.springframework.stereotype.Component;

@Component
public class VideosViewMapper implements Mapper<Videos, VideosView> {

    @Override
    public VideosView map(Videos videos) {
        return new VideosView(videos.getId(), videos.getTitulo(), videos.getDescricao(), videos.getUrl());
    }
}
