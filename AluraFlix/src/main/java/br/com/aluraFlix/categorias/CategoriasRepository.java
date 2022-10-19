package br.com.aluraFlix.categorias;


import br.com.aluraFlix.domain.Categorias;
import br.com.aluraFlix.domain.Videos;
import br.com.aluraFlix.videos.VideosView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {

    Optional<Categorias> findByTitulo(String titulo);

    @Query(value = "SELECT * FROM videos v INNER JOIN categorias c ON v.categoria_id = c.id", nativeQuery = true)
    List<VideosView> findVideosCategoria();

}
