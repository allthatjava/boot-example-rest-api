package brian.example.boot.rest.controller;

import brian.example.boot.rest.controller.exception.PersonNotFoundException;
import brian.example.boot.rest.controller.exception.SamePersonAlreadyExistException;
import brian.template.boot.rest.domain.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BootControllerAdvice extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(SamePersonAlreadyExistException.class)
	protected ResponseEntity<Object> handleDuplicatedRecord(SamePersonAlreadyExistException ex) {
		ApiError apiError = new ApiError(HttpStatus.CONFLICT);
		apiError.setMessage(ex.getMessage());
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
	
	@ExceptionHandler(PersonNotFoundException.class)
	protected ResponseEntity<Object> handlePersonNotFound(PersonNotFoundException ex){
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		apiError.setMessage(ex.getMessage());
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
