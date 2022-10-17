package br.com.aluraFlix.domain;

import br.com.aluraFlix.categorias.CategoriasForm;
import br.com.aluraFlix.videos.VideosForm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Categorias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String cor;

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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
