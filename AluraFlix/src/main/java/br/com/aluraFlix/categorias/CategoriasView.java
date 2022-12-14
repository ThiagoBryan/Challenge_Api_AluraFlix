package br.com.aluraFlix.categorias;

import br.com.aluraFlix.domain.Categorias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriasView {

    private Long id;
    private String titulo;
    private String cor;

}
