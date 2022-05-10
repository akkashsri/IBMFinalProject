package com.flightCommonSvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightApp.dto.FlightDto;
import com.flightCommonSvc.proxy.CommonProxy;
import com.flightCommonSvc.ui.FlightResponseModel;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1.0/flight")
@Slf4j
public class CommonController {

	private final CommonProxy commonProxy;

	private final ModelMapper modelMapper;

	public CommonController(CommonProxy commonProxy, ModelMapper modelMapper) {
		this.commonProxy = commonProxy;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/search")
	@CircuitBreaker(name = "flightWSCircuitBreaker", fallbackMethod = "flightWSFallBack")
	public ResponseEntity<List<FlightDto>> searchFlights(@RequestParam String source, @RequestParam String destination,
			@RequestParam String date, @RequestParam String seatType) {

		log.info("searchFlights called");

		final List<FlightDto> searchedFlights = commonProxy.searchFlights(source, destination, date, seatType);

		return ResponseEntity.status(HttpStatus.OK).body(searchedFlights);

	}

	@GetMapping("/flights")
	@CircuitBreaker(name = "flightWSCircuitBreaker", fallbackMethod = "flightWSFallBack")
	public ResponseEntity<List<FlightResponseModel>> getAllFlights() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		log.info("getAllFlights called");
		final List<FlightResponseModel> flights = new ArrayList<>();
		final List<FlightDto> res = commonProxy.getAllFlights();
		for (final FlightDto flightDto : res) {
			flights.add(modelMapper.map(flightDto, FlightResponseModel.class));
		}
		return ResponseEntity.status(HttpStatus.OK).body(flights);

	}

	public ResponseEntity<?> flightWSFallBack(Exception e) {
		log.info("called flightWSFallBack");
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
				.body("within flightWSFallBack method. COMMON-WS is down" + e.toString());
	}

}
