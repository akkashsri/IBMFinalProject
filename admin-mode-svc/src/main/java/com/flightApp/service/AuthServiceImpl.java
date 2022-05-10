package com.flightApp.service;

import org.springframework.stereotype.Service;

import com.flightApp.dto.AuthDto;
import com.flightApp.model.AuthEntity;
import com.flightApp.repo.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	public final UserRepository userRepository;

	@Override
	public AuthEntity validateUser(AuthDto authDto) {
		
		log.info("called validateUser");
		final AuthEntity user = userRepository.findByUserName(authDto.getUserName());

		if (user != null && user.getPassword().equals(authDto.getPassword())) {
			return user;
		} else {
			return null;

		}
	}
	

}
