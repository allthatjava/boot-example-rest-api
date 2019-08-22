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
	
//   @Override
//   protected ResponseEntity<Object> handleHttpMessageNotReadable(
//		   			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//       String error = "Malformed JSON request";
//       return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
//   }
//
//   private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
//       return new ResponseEntity<>(apiError, apiError.getStatus());
//   }

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
	       return new ResponseEntity<>(apiError, apiError.getStatus());
	   }
	
	@ExceptionHandler(SamePersonAlreadyExistException.class)
	protected ResponseEntity<Object> handleEntityNotFound(SamePersonAlreadyExistException ex) {
		ApiError apiError = new ApiError(HttpStatus.CONFLICT);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}
}
