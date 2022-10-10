package videos;


import br.com.aluraFlix.domain.Videos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideosRepository extends JpaRepository<Videos, Long> {

    Optional<Videos> findByUrl(String url);


}
