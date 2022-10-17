package br.com.aluraFlix.categorias;

import br.com.aluraFlix.videos.VideosView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriasController {

    @Autowired
    CategoriasService categoriasService;

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody @Valid CategoriasForm categoriasForm){
        return new ResponseEntity<String>(categoriasService.salvarCategoria(categoriasForm), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity <List<CategoriasView>> listasTodas(){
        return ResponseEntity.ok(categoriasService.todasCategorias());
    }

}
