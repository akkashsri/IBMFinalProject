package com.flightCommonSvc.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightApp.dto.FlightDto;

@FeignClient(value = "FLIGHT-WS")
public interface CommonProxy {
	
	@GetMapping("/airline/search/")
	public List<FlightDto> searchFlights(@RequestParam String source, @RequestParam String destination,
			@RequestParam String departureDate, @RequestParam String seatType);
	
	@GetMapping("/airline/flights")
	public List<FlightDto> getAllFlights();

}
