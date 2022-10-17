package br.com.aluraFlix.categorias;

import br.com.aluraFlix.exception.CategoriaException;
import br.com.aluraFlix.mapper.MapperCategorias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private MapperCategorias mapperCategorias;

    public String salvarCategoria(CategoriasForm categoriasForm){
        categoriasRepository.findByTitulo(categoriasForm.getTitulo()).ifPresent(categorias -> {
            throw new CategoriaException("Categoria já existe");
        });
        categoriasRepository.save(mapperCategorias.converterCategorias(categoriasForm));
        return "Categoria salva com sucesso";
    }


}
