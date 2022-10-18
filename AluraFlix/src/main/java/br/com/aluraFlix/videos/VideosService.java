package br.com.aluraFlix.videos;

import br.com.aluraFlix.domain.Videos;
import br.com.aluraFlix.exception.VideosException;
import br.com.aluraFlix.mapper.MapperVideos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideosService {
    @Autowired
    private VideosRepository videosRepository;

    @Autowired
    private MapperVideos mapper;

    public String salvarVideo(VideosForm videosForm) {
        videosRepository.findByUrl(videosForm.getUrl()).ifPresent(videos -> {
            throw new VideosException("Video já existe");
        });

        //Videos videos = new Videos();
        videosRepository.save(mapper.converterVideos(videosForm));
        return "Video salvo com sucesso.";
    }

    public List<VideosProjection> todosVideos() {
        return videosRepository.findAllVideos();
    }

    public VideosView mostrarVideoId(Long videoId) {
        Videos video = videosRepository.findById(videoId).orElseThrow(() -> new VideosException("Video não encontrado"));
        return mapper.converterVideos(video);
    }

    public String atualizar(Long IdVideos, VideosForm videosForm) {
        Videos video = videosRepository.findById(IdVideos).orElseThrow(() -> new VideosException("Video não encontrado"));
        videosRepository.save(video.atualizarVideo(video, videosForm));

        return "O Vídeo com ID " + video.getId() + " foi atualizado";

    }

    public void deletar(Long videoId) {
        videosRepository.deleteById(videoId);

    }




}
