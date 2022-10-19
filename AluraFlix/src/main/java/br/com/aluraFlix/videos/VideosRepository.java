package br.com.aluraFlix.videos;


import br.com.aluraFlix.domain.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface VideosRepository extends JpaRepository<Videos, Long> {

    Optional<Videos> findByUrl(String url);

    @Query(value = "SELECT " +
                    "new br.com.aluraFlix.videos.VideosView(v.id, v.titulo, v.descricao, v.url, v.categoria)" +
                "FROM Videos v")
    List<VideosView> findAllVideos();

    @Query(value = "SELECT " +
            "new br.com.aluraFlix.videos.VideosView(v.id, v.titulo, v.descricao, v.url, v.categoria)" +
            "FROM Videos v " +
            "WHERE v.titulo = :titulo")
    Optional<VideosView> findByTitulo(String titulo);


}
