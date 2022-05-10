package com.flightApp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;

}
