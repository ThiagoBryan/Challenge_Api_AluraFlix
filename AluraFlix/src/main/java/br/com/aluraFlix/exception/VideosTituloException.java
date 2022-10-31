package br.com.aluraFlix.exception;

public class VideosTituloException extends IllegalArgumentException{

    private static final long serialVersionUID = 1L;

    public VideosTituloException(String message) {
        super(message);
    }
}
