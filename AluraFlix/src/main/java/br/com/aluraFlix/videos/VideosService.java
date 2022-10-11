package br.com.aluraFlix.videos;

import br.com.aluraFlix.domain.Videos;
import br.com.aluraFlix.exception.VideosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class VideosService {
    @Autowired
    private  VideosRepository videosRepository;
    @Autowired
    private  VideosFormMapper videosFormMapper;
    @Autowired
    private VideosViewMapper videosViewMapper;

    public String salvarVideo(VideosForm form) {
        videosRepository.findByUrl(form.getUrl()).ifPresent(videos -> {
            throw new VideosException("Video já existe");
        });

        Videos salvarVideos = videosFormMapper.map(form);
        videosRepository.save(salvarVideos);
        return "Video criado com Id: " + salvarVideos.getId();
    }

    public List<VideosView> todosVideos() {
        return videosRepository.findAll().stream().map(videosViewMapper::map).collect(toList());
    }





}
