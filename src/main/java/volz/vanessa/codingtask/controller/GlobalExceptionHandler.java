package volz.vanessa.codingtask.controller;

import java.io.IOException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({IOException.class})
	protected ResponseEntity<Object> handleIOException(IOException exception) {
		//For demonstration purposes, handling IOException
		return ResponseEntity.internalServerError().body(exception.getMessage());
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	protected ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception) {
		return ResponseEntity.notFound().build();
	}
	
}
