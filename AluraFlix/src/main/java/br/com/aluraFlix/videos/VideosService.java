package br.com.aluraFlix.videos;

import br.com.aluraFlix.domain.Videos;
import br.com.aluraFlix.exception.VideosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class VideosService {
    @Autowired
    private  VideosRepository videosRepository;

    public String salvarVideo(VideosForm videosForm) {
        videosRepository.findByUrl(videosForm.getUrl()).ifPresent(videos -> {
            throw new VideosException("Video já existe");
        });

        Videos videos = new Videos();
        videosRepository.save(videos.converter(videosForm));
        return "Video salvo com sucesso.";
    }

    public List<VideosView> todosVideos() {
        List<Videos> videos = videosRepository.findAll(); // do banco
        List<VideosView> videosViews = new ArrayList<>(); // lista vazia para popular
            videos.forEach(video->{ // for each do q esta vindo do banco
                VideosView view = new VideosView(); // cada video transforma em um objeto view
                videosViews.add(view.converter(video));//  chama o metodo para converter e adiciona o que converteu
            });
            return videosViews;
    }

    public String atualizar(Long IdVideos, VideosForm videosForm) {
        Optional<Videos> videos = videosRepository.findById(IdVideos);
        if (videos.isPresent()) {
            Videos atualizarVideos = videos.get();
            atualizarVideos.setTitulo(videosForm.getTitulo());
            atualizarVideos.setDescricao(videosForm.getDescricao());
            videosRepository.save(atualizarVideos);
        }
        return "O Vídeo com ID " + videos.get().getId() + " foi atualizado";

    }





}
