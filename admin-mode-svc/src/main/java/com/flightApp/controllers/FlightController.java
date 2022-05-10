package com.flightApp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightApp.dto.FlightDto;
import com.flightApp.exception.FlightException;
import com.flightApp.proxy.FlightProxy;
import com.flightApp.ui.FlightRequestModel;
import com.flightApp.ui.FlightResponseModel;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/api/admin/v1.0/flight")
@AllArgsConstructor
public class FlightController {
	
	private final ModelMapper modelMapper;
	private final FlightProxy flightProxy;
	
	@PostMapping()
	@CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "myTestFallBack")
	public ResponseEntity<FlightResponseModel> addNewFlight(@RequestBody final FlightRequestModel flightRequestModel) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		log.info("started addNewFlight **");

		final FlightDto flightDto = modelMapper.map(flightRequestModel, FlightDto.class);
		final FlightDto res = flightProxy.addFlight(flightDto);
		if (res.getFlightId().isEmpty()) {
			log.error("failed to add new Flight.");
			throw new FlightException("failed to add new Flight.");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(res, FlightResponseModel.class));

	}
	
	@PutMapping("/{flightId}")
	@CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "myTestFallBack")
	public ResponseEntity<FlightResponseModel> blockFlight(@PathVariable final String flightId) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		log.info("started blockFlight **");
		final FlightDto res = modelMapper.map(flightProxy.blockFlight(flightId), FlightDto.class);
		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(res, FlightResponseModel.class));

	}
	
	@GetMapping("/flights")
	@CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "myTestFallBack")
	public ResponseEntity<List<FlightResponseModel>> getAllFlights() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		log.info("started getAllFlights **");
		final List<FlightResponseModel> flights = new ArrayList<>();
		final List<FlightDto> res = flightProxy.getAllFlights();
		for (final FlightDto flightDto : res) {
			flights.add(modelMapper.map(flightDto, FlightResponseModel.class));
		}
		return ResponseEntity.status(HttpStatus.OK).body(flights);

	}
	
	public ResponseEntity<?> myTestFallBack(final Exception exception) {
		log.info("started myTestFallBack **");
		return ResponseEntity.ok("within myTestFallBack method. FLIGHT-WS is down" + exception.toString());
	}

}
