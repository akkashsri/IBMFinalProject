package com.flightApp.controller;

import java.util.List;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightApp.dto.FlightDto;
import com.flightApp.service.FlightService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController()
@RequestMapping("/airline")
@Log4j2
@AllArgsConstructor
public class FlightController {
	
	private FlightService flightService;

	private ModelMapper modelMapper;
	
	@PostMapping("/inventory/add")
	
	public ResponseEntity<FlightDto> addNewFlight(@Validated @RequestBody FlightDto flightDto) {
		log.info("addNewFlight called");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Random random = new Random();
		Integer number = Math.abs(random.nextInt());
		flightDto.setFlightId(number.toString());
		FlightDto res = flightService.addFlight(flightDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);

	}


	@PutMapping("/inventory/block/{flightId}")
	
	public ResponseEntity<FlightDto> blockFlight(@PathVariable String flightId) {
		log.info("blockFlight called");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		FlightDto res = flightService.blockFlight(flightId);

		return ResponseEntity.status(HttpStatus.OK).body(res);

	}

	

	@GetMapping("/search/flights")
	
	public ResponseEntity<List<FlightDto>> searchFlights(@RequestParam String source, @RequestParam String destination,
			@RequestParam String departureDate, @RequestParam String seatType) {
		log.info("searchFlights called");
		List<FlightDto> res = flightService.searchFlights(source, destination, departureDate,seatType);

		return ResponseEntity.status(HttpStatus.OK).body(res);

	}

	
	@GetMapping("/flights")
	
	public ResponseEntity<List<FlightDto>> getAllFlights() {
		log.info("getAllFlights called");
		List<FlightDto> res = flightService.getAllFlights();
		return ResponseEntity.status(HttpStatus.OK).body(res);

	}
	
	@PutMapping("/flight")
	
	public ResponseEntity<FlightDto> updateFlight(@RequestParam String flightId, @RequestParam int seats,@RequestParam String seatType) {
		log.info("updateFlight called");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		FlightDto res = flightService.updateFlight(flightId,seats,seatType);

		return ResponseEntity.status(HttpStatus.OK).body(res);

	}


}
