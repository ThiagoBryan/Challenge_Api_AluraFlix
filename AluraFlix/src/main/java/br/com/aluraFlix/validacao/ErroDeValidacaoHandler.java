package br.com.aluraFlix.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler { // Classe para personalizar o erro
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)// altera o status code devolvodo como resposta
	@ExceptionHandler(MethodArgumentNotValidException.class) // vai mostrar essa excepetion para todas as classes
	public List<ErrorDeFormularioDto> handle(MethodArgumentNotValidException exception) { // cria uma lista para ter quais erros irao retornar
		List<ErrorDeFormularioDto> dto = new ArrayList<>();
	
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();// contem todos os erros de field
		fieldErrors.forEach(e -> {// para percorrer a lista	//LocaleContextHolder.getLocale para no postman pegar o idioma; headers Key= Accept Language, VALUE = o idioma ex en-US  			
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErrorDeFormularioDto erro = new ErrorDeFormularioDto(e.getField(), mensagem);
			dto.add(erro);// pega o dto e adiciona a mensagem de erro
		});
		
		return dto;
	}
}
