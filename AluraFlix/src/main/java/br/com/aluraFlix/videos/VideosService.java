package br.com.aluraFlix.videos;

import br.com.aluraFlix.domain.Categorias;
import br.com.aluraFlix.domain.Videos;
import br.com.aluraFlix.exception.VideosException;
import br.com.aluraFlix.exception.VideosTituloException;
import br.com.aluraFlix.mapper.MapperVideos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VideosService {
    @Autowired
    private VideosRepository videosRepository;

    @Autowired
    private MapperVideos mapper;

    public Videos salvarVideo(VideosForm videosForm) {
        videosRepository.findByUrl(videosForm.getUrl()).ifPresent(videos -> {
            throw new VideosException("Video já existe");
        });
        Videos entity = mapper.converterVideos(videosForm);
        videosRepository.save(entity);
        return entity;
    }
        // TODOS OS VIDEOS SEM PAGINAÇÃO
//    public List<VideosView> todosVideos() {
//        return videosRepository.findAllVideos();
//    }
            //PAGINAÇÃO
    public Page<VideosView> todosVideos(Pageable pageable) {
        Page<Videos> videos = videosRepository.findAll(pageable);
        List<VideosView> videosView = videos.getContent()
                .stream()
                .map(v -> mapper.converterVideos(v))
                .collect(Collectors.toList());

        return new PageImpl<VideosView>(videosView, pageable, videos.getTotalElements());
    }


    public VideosView mostrarVideoId(Long videoId) {
        Videos video = videosRepository.findById(videoId).orElseThrow(() -> new VideosException("Video com ID informado não existe"));
        return mapper.converterVideos(video);
    }

    public Optional<VideosView> mostrarVideoPorTitulo(String titulo) {
        return videosRepository.findByTitulo(titulo);
    }

    public void atualizar(Long IdVideos, VideosForm videosForm) {
        Videos video = videosRepository.findById(IdVideos).orElseThrow(() -> new VideosException("Video com ID informado não existe"));
        videosRepository.save(video.atualizarVideo(video, videosForm));

    }

    public void deletar(Long videoId) {
        Optional<Videos> video = videosRepository.findById(videoId);
        videosRepository.delete(video.get());

    }


}
