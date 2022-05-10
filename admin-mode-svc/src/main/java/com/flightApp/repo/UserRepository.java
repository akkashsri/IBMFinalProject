package com.flightApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flightApp.model.AuthEntity;

@Repository
public interface UserRepository extends JpaRepository<AuthEntity, Integer>{
	
	@Query
	public AuthEntity findByUserName(final String userName);

}
