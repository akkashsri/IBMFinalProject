package com.flightApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.flightApp.ui.ErrorResponseModel;

@ControllerAdvice
public class FlightExceptionHandler {
	
	@ExceptionHandler(FlightException.class)
	public ResponseEntity<?> handleFlightException(final FlightException exception){
		
		ErrorResponseModel error = new ErrorResponseModel();
		
		error.setCode(HttpStatus.BAD_REQUEST);
		error.setMessage(exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}

}
