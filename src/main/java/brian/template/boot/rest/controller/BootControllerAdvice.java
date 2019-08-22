package brian.template.boot.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import brian.template.boot.rest.controller.exception.SamePersonAlreadyExistException;
import brian.template.boot.rest.domain.ApiError;

@ControllerAdvice
public class BootControllerAdvice extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(SamePersonAlreadyExistException.class)
	protected ResponseEntity<Object> handleEntityNotFound(SamePersonAlreadyExistException ex) {
		ApiError apiError = new ApiError(HttpStatus.CONFLICT);
		apiError.setMessage(ex.getMessage());
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
