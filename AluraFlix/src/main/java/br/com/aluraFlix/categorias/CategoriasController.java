package br.com.aluraFlix.categorias;

import br.com.aluraFlix.exception.CategoriaException;
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
    public ResponseEntity<String> salvar(@RequestBody @Valid CategoriasForm categoriasForm) throws CategoriaException {
        return new ResponseEntity<String>(categoriasService.salvarCategoria(categoriasForm), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoriasView>> listasTodas() {
        return ResponseEntity.ok(categoriasService.todasCategorias());
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoriasView> mostrarporID(@PathVariable Long id) throws CategoriaException {
        return ResponseEntity.ok(categoriasService.mostrarCategoriaId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> atualizarCategoria(@PathVariable Long id,  @Valid @RequestBody CategoriasForm categoriasForm) throws CategoriaException{
        return ResponseEntity.ok(categoriasService.atualizar(id, categoriasForm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        categoriasService.deletar(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
