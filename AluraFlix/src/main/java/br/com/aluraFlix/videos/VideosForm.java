package br.com.aluraFlix.videos;

import br.com.aluraFlix.domain.Categorias;
import br.com.aluraFlix.domain.Videos;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
@AllArgsConstructor
public class VideosForm {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotBlank
    private String url;
    @NotBlank
    private Categorias categoria;

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
