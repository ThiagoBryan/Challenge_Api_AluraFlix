package br.com.aluraFlix.mapper;

import br.com.aluraFlix.categorias.CategoriasForm;
import br.com.aluraFlix.categorias.CategoriasView;
import br.com.aluraFlix.domain.Categorias;
import br.com.aluraFlix.domain.Videos;
import br.com.aluraFlix.videos.VideosForm;
import br.com.aluraFlix.videos.VideosView;
import org.springframework.stereotype.Component;

@Component
public class MapperCategorias {

    public CategoriasView converterCategorias(Categorias categorias) {
        return new CategoriasView(categorias.getId(), categorias.getTitulo(), categorias.getCor());
    }

    public Categorias converterCategorias(CategoriasForm categoriasForm) {
        return new Categorias(categoriasForm.getTitulo(), categoriasForm.getCor());
    }
}
