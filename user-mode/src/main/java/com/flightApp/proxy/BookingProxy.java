package com.flightApp.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightApp.dto.BookingDto;
import com.flightApp.dto.FlightDto;

@FeignClient(value = "FLIGHT-WS")
public interface BookingProxy {

	@PostMapping("/booking")
	public BookingDto bookFlight(@RequestBody BookingDto bookingDto);

	@GetMapping("/airline/search/flights")
	public List<FlightDto> searchFlights(@RequestParam String source, @RequestParam String destination,
			@RequestParam String departureDate, @RequestParam String seatType);

	@PutMapping("/booking/cancel/{pnrNumber}")
	public BookingDto cancelTicket(@PathVariable String pnrNumber);

	@GetMapping("/airline/ticket/{pnrNumber}")
	public BookingDto findTicket(@PathVariable String pnrNumber);

	@GetMapping("/booking/{email}")
	public List<BookingDto> getUserTickets(@PathVariable String email);

	@PutMapping("/airline/flight")
	public FlightDto updateFlight(@RequestParam String flightId, @RequestParam int seats,
			@RequestParam String seatType);

}
