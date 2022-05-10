package com.flightApp.controller;

import java.util.List;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flightApp.dto.BookingDto;
import com.flightApp.service.BookingService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@AllArgsConstructor
public class BookingController {
	
	private BookingService bookingService;
	private ModelMapper modelMapper;



	@PostMapping("/booking")
	public ResponseEntity<BookingDto> bookFlight(@RequestBody BookingDto bookingDto) {
		log.info("book flight called");
		Random random = new Random();
		Integer number = Math.abs(random.nextInt());
		bookingDto.setPnrNumber(Integer.toString(number));
		BookingDto res = bookingService.bookFlight(bookingDto);
		return ResponseEntity.status(HttpStatus.OK).body(res);

	}


	@GetMapping("/booking/{email}")

	public ResponseEntity<List<BookingDto>> getUserTickets(@PathVariable String email) {
		log.info("getUserTickets called");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<BookingDto> res = bookingService.getUserTickets(email);

		return ResponseEntity.status(HttpStatus.OK).body(res);

	}


	@PutMapping("/booking/cancel/{pnrNumber}")

	public ResponseEntity<BookingDto> cancelTicket(@PathVariable String pnrNumber) {
		log.info("cancelTicket called");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		BookingDto res = bookingService.cancelTicket(pnrNumber);

		return ResponseEntity.status(HttpStatus.OK).body(res);

	}


	@GetMapping("/airline/ticket/{pnrNumber}")
	public ResponseEntity<BookingDto> findTicket(@PathVariable String pnrNumber) {
		log.info("findTicket called");
		BookingDto res = bookingService.findTicket(pnrNumber);

		return ResponseEntity.status(HttpStatus.OK).body(res);

	}

}
