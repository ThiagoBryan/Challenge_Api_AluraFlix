package br.com.aluraFlix.videos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<List<VideosView>> listarTodos(){
        return ResponseEntity.ok(videosService.todosVideos());
    }

}
