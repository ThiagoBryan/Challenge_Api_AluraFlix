package br.com.aluraFlix.domain;

import br.com.aluraFlix.categorias.CategoriasForm;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Categorias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String cor;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<Videos> videos;

    public Categorias(Long id, String titulo, String cor, List<Videos> videos) {
        this.id = id;
        this.titulo = titulo;
        this.cor = cor;
        this.videos = videos;
    }

    public List<Videos> getVideos() {
        return videos;
    }

    public void setVideos(List<Videos> videos) {
        this.videos = videos;
    }

    public Categorias() {
    }

    public Categorias(String titulo, String cor) {
        this.titulo = titulo;
        this.cor = cor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String tipo) {
        this.titulo = tipo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Categorias atualizarCategoria(Categorias categoria, CategoriasForm categoriasForm){
        categoria.setTitulo(categoriasForm.getTitulo());
        categoria.setCor(categoriasForm.getCor());
        return categoria;
    }



}
