package org.jsp.springDataJpa.exception;

import org.jsp.springDataJpa.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class UseBootApplicationException extends ResponseEntityExceptionHandler {
	@ExceptionHandler(IdNotFoundExcepton.class)
	public ResponseEntity<ResponseStructure<String>> handleINFE(IdNotFoundExcepton exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage(exception.getMessage());
		structure.setData("user not found");
		
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		}
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ResponseStructure<String>> handleICE(InvalidCredentialsException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage(exception.getMessage());
		structure.setData("user not found");
		
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		}
	
	

}
