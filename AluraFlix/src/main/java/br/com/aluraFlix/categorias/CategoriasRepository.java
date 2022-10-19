package br.com.aluraFlix.categorias;


import br.com.aluraFlix.domain.Categorias;
import br.com.aluraFlix.domain.Videos;
import br.com.aluraFlix.videos.VideosView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {

    Optional<Categorias> findByTitulo(String titulo);

    @Query(value = "SELECT new br.com.aluraFlix.videos.VideosView(v.id, v.titulo, v.descricao, v.url, v.categoria) FROM Videos v " +
            "JOIN Categorias c ON c.id = v.categoria " +
            "WHERE c.id = :id")
    List<VideosView> findVideosByCategoriaId(@Param("id") Long id);

}
