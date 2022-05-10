package com.flightApp.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.flightApp.dto.FlightDto;

@FeignClient(value = "FLIGHT-WS")
public interface FlightProxy {

	@PostMapping("/airline/inventory/add")
	FlightDto addFlight(@RequestBody FlightDto flightDto);

	@PutMapping("/airline/inventory/block/{flightId}")
	public FlightDto blockFlight(@PathVariable String flightId);

	@GetMapping("/airline/flights")
	public List<FlightDto> getAllFlights();

}
