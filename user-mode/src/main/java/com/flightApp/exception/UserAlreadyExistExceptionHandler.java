package com.flightApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.flightApp.ui.ErrorResponseModel;

@ControllerAdvice
public class UserAlreadyExistExceptionHandler {
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<ErrorResponseModel> handleUserAlreadyExistException(UserAlreadyExistException e) {
		ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST, e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorResponseModel);

	}

}
