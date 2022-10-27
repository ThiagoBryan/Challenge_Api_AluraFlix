package br.com.aluraFlix.categorias;

import br.com.aluraFlix.domain.Categorias;
import br.com.aluraFlix.exception.CategoriaException;
import br.com.aluraFlix.mapper.MapperCategorias;
import br.com.aluraFlix.videos.VideosView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private MapperCategorias mapperCategorias;

    public String salvarCategoria(CategoriasForm categoriasForm) {
        categoriasRepository.findByTitulo(categoriasForm.getTitulo()).ifPresent(categorias -> {
            throw new CategoriaException("Categoria já existe");
        });
        categoriasRepository.save(mapperCategorias.converterCategorias(categoriasForm));
        return "Categoria salva com sucesso";
    }

    //SEM PAGINAÇÃO
//    public List<CategoriasView> todasCategorias(){
//        List<Categorias> categorias = categoriasRepository.findAll();
//        List<CategoriasView> categoriasView = new ArrayList<>();
//        categorias.forEach(categoria -> {
//            categoriasView.add(mapperCategorias.converterCategorias(categoria));
//        });
//        return categoriasView;
//    }

    //COM PAGINAÇÃO
    public Page<CategoriasView> todasCategorias(Pageable pageable) {
        Page<Categorias> categorias = categoriasRepository.findAll(pageable);
        List<CategoriasView> categoriasView = categorias.getContent()
                .stream()
                .map(c -> mapperCategorias.converterCategorias(c))
                .collect(Collectors.toList());

        return new PageImpl<CategoriasView>(categoriasView, pageable, categorias.getTotalElements());
    }

    public CategoriasView mostrarCategoriaId(Long categoriaID) {
        Categorias categoria = categoriasRepository.findById(categoriaID).orElseThrow(() -> new CategoriaException("Categoria não encontrada"));
        return mapperCategorias.converterCategorias(categoria);
    }

    public List<VideosView> mostrarVideosPorCategoria(Long id) {
        return categoriasRepository.findVideosByCategoriaId(id);
    }

    public String atualizar(Long categoriaId, CategoriasForm categoriasForm) {
        Categorias categoria = categoriasRepository.findById(categoriaId).orElseThrow(() -> new CategoriaException("Categoria não encontarda"));
        categoriasRepository.save(categoria.atualizarCategoria(categoria, categoriasForm));

        return "A categoria com ID " + categoria.getId() + " foi atualizada";
    }

    public void deletar(Long categoriaID) {
        categoriasRepository.deleteById(categoriaID);
    }


}
