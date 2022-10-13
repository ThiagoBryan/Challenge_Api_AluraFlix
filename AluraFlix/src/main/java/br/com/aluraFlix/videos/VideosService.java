package br.com.aluraFlix.videos;

import br.com.aluraFlix.domain.Videos;
import br.com.aluraFlix.exception.VideosException;
import br.com.aluraFlix.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideosService {
    @Autowired
    private VideosRepository videosRepository;

    @Autowired
    private Mapper mapper;

    public String salvarVideo(VideosForm videosForm) {
        videosRepository.findByUrl(videosForm.getUrl()).ifPresent(videos -> {
            throw new VideosException("Video já existe");
        });

        //Videos videos = new Videos();
        videosRepository.save(mapper.converter(videosForm));
        return "Video salvo com sucesso.";
    }

    public List<VideosView> todosVideos() {
        List<Videos> videos = videosRepository.findAll(); // do banco
        List<VideosView> videosViews = new ArrayList<>(); // lista vazia para popular
        videos.forEach(video -> { // for each do q esta vindo do banco
            videosViews.add(mapper.converter(video));//  chama o metodo para converter e adiciona o que converteu
        });
        return videosViews;
    }

    public VideosView mostrarVideoId(Long videoId) {
        Videos video = videosRepository.findById(videoId).orElseThrow(() -> new VideosException("Video não encontrado"));
        return mapper.converter(video);
    }

    public String atualizar(Long IdVideos, VideosForm videosForm) {
        Videos video = videosRepository.findById(IdVideos).orElseThrow(() -> new VideosException("Video não encontrado"));
        videosRepository.save(video.atualizarVideo(video, videosForm));

        return "O Vídeo com ID " + video.getId() + " foi atualizado";

    }


}
