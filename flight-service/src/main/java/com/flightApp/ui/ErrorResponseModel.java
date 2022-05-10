package com.flightApp.ui;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ErrorResponseModel {
	
	private HttpStatus code;

	private String message;
	
    private Long errorReportingTime;

}
