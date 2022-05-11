package com.flightApp.services;

import com.flightApp.dto.AuthorizationDto;
import com.flightApp.model.AuthorizationEntity;

public interface AuthorizationService {
	
	public AuthorizationEntity validateUser(AuthorizationDto user);

}
