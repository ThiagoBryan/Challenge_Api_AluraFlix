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

    @Query(value = "SELECT v.id, v.titulo, v.descricao, v.url, v.categoria_id as categoria FROM videos v", nativeQuery = true)
    List<VideosProjection> findAllVideos();

}
