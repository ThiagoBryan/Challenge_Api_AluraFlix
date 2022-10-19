package br.com.aluraFlix.videos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideosController {
    @Autowired
    private VideosService videosService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody VideosForm videosForm) {
        return new ResponseEntity<String>(videosService.salvarVideo(videosForm), HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<VideosView>> listarTodos(){
        return ResponseEntity.ok(videosService.todosVideos());
    }

    @GetMapping("/{id}")
        public ResponseEntity<VideosView> mostrarVideoId(@PathVariable Long id){
        return ResponseEntity.ok(videosService.mostrarVideoId(id));
    }

    @GetMapping
        public ResponseEntity<VideosView> mostrarVideoPorTitulo(@RequestParam("titulo") String titulo)  {
        return ResponseEntity.ok(videosService.mostrarVideoPorTitulo(titulo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @Valid @RequestBody VideosForm videosForm){
        return ResponseEntity.ok(videosService.atualizar(id, videosForm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        videosService.deletar(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
