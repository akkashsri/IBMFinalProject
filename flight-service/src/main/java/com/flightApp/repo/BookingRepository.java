package com.flightApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightApp.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{
	
	List<Booking> findBySourceAndDestination(String source, String destination);

	Booking findByEmail(String email);

	Booking findByPnrNumber(String pnrNumber);

	List<Booking> findAllByEmail(String email);

}
