package br.com.aluraFlix.videos;

import br.com.aluraFlix.domain.Videos;
import br.com.aluraFlix.infra.Mapper;
import org.springframework.stereotype.Component;


    @Component
    public class VideosFormMapper implements Mapper<VideosForm, Videos> {

    @Override
    public Videos map(VideosForm source) {

        return new Videos(source.getTitulo(), source.getDescricao(), source.getUrl());
    }
}
