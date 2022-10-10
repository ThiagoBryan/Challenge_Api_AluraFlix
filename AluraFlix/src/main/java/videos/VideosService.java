package videos;

import br.com.aluraFlix.domain.Videos;
import exception.VideosException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VideosService {

    private final VideosRepository videosRepository;

    private final VideosFormMapper videosFormMapper;

    public String salvar(VideosForm form) {
        videosRepository.findByUrl(form.getUrl()).ifPresent(videos -> {
            throw new VideosException("Video já existe");
        });

        Videos salvarVideos = videosFormMapper.map(form);
        videosRepository.save(salvarVideos);
        return "Video criado com Id: " + salvarVideos.getId();


    }

}
