package br.com.aluraFlix.videos;

import br.com.aluraFlix.domain.Categorias;

public interface VideosProjection {

     Long getId();
     String getTitulo();
     String getDescricao();
     String getUrl();
     Long getCategoria();


}
