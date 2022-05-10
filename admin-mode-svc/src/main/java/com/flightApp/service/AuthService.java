package com.flightApp.service;

import com.flightApp.dto.AuthDto;
import com.flightApp.model.AuthEntity;

public interface AuthService {
	
	AuthEntity validateUser(AuthDto user);

}
