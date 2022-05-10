package com.flightApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.flightApp.ui.ErrorResponseModel;

@ControllerAdvice
public class BookingExceptionHandler {
	
	@ExceptionHandler(BookingException.class)
	public ResponseEntity<ErrorResponseModel> handleBookingException(BookingException e){
		
		ErrorResponseModel errorResponseModel = new ErrorResponseModel();
		
		errorResponseModel.setCode(HttpStatus.NOT_FOUND);
		errorResponseModel.setMessage(e.getMessage());
		errorResponseModel.setErrorReportingTime(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseModel);
	}

}
