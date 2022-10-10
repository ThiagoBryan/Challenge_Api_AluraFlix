package videos;

import br.com.aluraFlix.domain.Videos;
import exception.VideosException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VideosService {

    private final VideosRepository videosRepository;


}
