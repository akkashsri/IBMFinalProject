package com.flightApp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;

}
