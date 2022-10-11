package br.com.aluraFlix.videos;


import br.com.aluraFlix.domain.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VideosRepository extends JpaRepository<Videos, Long> {

    Optional<Videos> findByUrl(String url);


}
