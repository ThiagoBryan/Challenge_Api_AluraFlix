package br.com.aluraFlix.validacao;

import br.com.aluraFlix.exception.CategoriaException;
import br.com.aluraFlix.exception.VideosException;
import br.com.aluraFlix.exception.VideosTituloException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroExceptionHandler {

    @ExceptionHandler(CategoriaException.class)
    public ResponseEntity<String> CategoriaException(CategoriaException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(VideosException.class)
    public ResponseEntity<String> VideosException(VideosException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(VideosTituloException.class)
    public ResponseEntity<String> VideosTituloException(VideosTituloException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }



}
