package br.com.aluraFlix.categorias;

import javax.validation.constraints.NotBlank;

public class CategoriasForm {

    @NotBlank
    private String titulo;
    @NotBlank
    private String cor;

    public CategoriasForm() {
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
}
