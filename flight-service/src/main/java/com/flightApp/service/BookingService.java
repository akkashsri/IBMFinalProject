package com.flightApp.service;

import java.util.List;

import com.flightApp.dto.BookingDto;

public interface BookingService {
	
	BookingDto bookFlight(BookingDto bookingDto);

	BookingDto cancelTicket(String pnrNumber);

	BookingDto findTicket(String pnrNumber);

	List<BookingDto> getUserTickets(String email);

}
