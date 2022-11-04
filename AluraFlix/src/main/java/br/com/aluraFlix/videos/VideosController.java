package br.com.aluraFlix.videos;

import br.com.aluraFlix.domain.Videos;
import br.com.aluraFlix.exception.VideosTituloException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideosController {
    @Autowired
    private VideosService videosService;

    @PostMapping
    public ResponseEntity<VideosView> save(@RequestBody VideosForm videosForm) {
        Videos videos = videosService.salvarVideo(videosForm);
        URI uri = URI.create("/todos");
        return ResponseEntity.created(uri).body(new VideosView(videos.getId(), videosForm.getTitulo(), videosForm.getDescricao(), videosForm.getUrl(), videosForm.getCategoria()));
    }

    //PAGINAÇÃO
    @GetMapping("/todos")
    public ResponseEntity<Page<VideosView>> listarTodos(@PageableDefault(page = 0, size = 5, sort = "titulo") Pageable pageable) {
        return ResponseEntity.ok(videosService.todosVideos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideosView> mostrarVideoId(@PathVariable Long id) {
        return ResponseEntity.ok(videosService.mostrarVideoId(id));
    }

    @GetMapping
    public ResponseEntity<VideosView> mostrarVideoPorTitulo(@RequestParam("titulo") String titulo) {
        return ResponseEntity.ok(videosService.mostrarVideoPorTitulo(titulo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideosForm> atualizar(@PathVariable Long id, @Valid @RequestBody VideosForm videosForm) {
        videosService.atualizar(id, videosForm);
        return ResponseEntity.ok(new VideosForm(videosForm.getTitulo(), videosForm.getDescricao(), videosForm.getUrl(), videosForm.getCategoria()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(videosService.deletar(id));
    }

}
