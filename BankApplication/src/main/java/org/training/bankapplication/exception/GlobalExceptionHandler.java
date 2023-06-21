package org.training.bankapplication.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<String>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ErrorResponse error = new ErrorResponse(5000L, details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerIdNotFoundException.class)
	protected ResponseEntity<Object> customeridnotfoundexception(CustomerIdNotFoundException ex, WebRequest request) {
		List<String> details = new ArrayList<String>();

		details.add(ex.getLocalizedMessage());

		ErrorResponse error = new ErrorResponse(5000L, details);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AccountNotFoundException.class)
	protected ResponseEntity<Object> accountidnotfoundexception(AccountNotFoundException ex, WebRequest request) {
		List<String> details = new ArrayList<String>();

		details.add(ex.getLocalizedMessage());

		ErrorResponse error = new ErrorResponse(5000L, details);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoTransactionFoundException.class)
	protected ResponseEntity<Object> notransactionfoundexception(NoTransactionFoundException ex, WebRequest request) {
		List<String> details = new ArrayList<String>();

		details.add(ex.getLocalizedMessage());

		ErrorResponse error = new ErrorResponse(5000L, details);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<Object> dataintegrityviolationexception(DataIntegrityViolationException ex,
			WebRequest request) {

		return new ResponseEntity<>("Email should be unique", HttpStatus.NOT_FOUND);
	}

}
