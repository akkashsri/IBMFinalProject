package com.flightApp.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flightApp.dto.AuthorizationDto;
import com.flightApp.model.AuthorizationEntity;
import com.flightApp.repo.AuthorizationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
	
	public final AuthorizationRepository userRepository;

	@Override
	public AuthorizationEntity validateUser(AuthorizationDto user) {
		
		Optional<AuthorizationEntity> resp = userRepository.findByUserName(user.getUserName());
		
		if(resp.isPresent()) {
			
			AuthorizationEntity user1 = resp.get();
			if(user1.getPassword().equals(user.getPassword())) {
				
				return user1;
			} else {
				return null;
			}
		} else {
			
			return null;
		}
	}

}
