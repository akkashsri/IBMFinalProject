package com.flightApp.service;

import java.util.List;

import com.flightApp.dto.FlightDto;

public interface FlightService {
	
	public FlightDto addFlight(FlightDto flightDto);
	
	public FlightDto blockFlight(String flightId);

	public List<FlightDto> searchFlights(String source, String destination,String departureDate,String seatType);

	public List<FlightDto> getAllFlights();

	public FlightDto updateFlight(String flightId, int seats, String seatType);

}
