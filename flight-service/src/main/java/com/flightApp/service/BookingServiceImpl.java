package com.flightApp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.flightApp.dto.BookingDto;
import com.flightApp.exception.BookingException;
import com.flightApp.model.Booking;
import com.flightApp.repo.BookingRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
	
	private final ModelMapper modelMapper;
	private final BookingRepository bookingRepository;

	@Override
	public BookingDto bookFlight(BookingDto bookingDto) {
		log.info("bookFlight called");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		bookingDto.setStatus("booked");
		Booking booking = modelMapper.map(bookingDto, Booking.class);
		Booking bookedTicket = bookingRepository.save(booking);
		
//		if (bookedTicket.getPnrNumber() != null) {
//			sendMail(booking);
//		}
		
		return modelMapper.map(bookedTicket, BookingDto.class);
	}

	@Override
	public BookingDto cancelTicket(String pnrNumber) {
		
		log.info("cancelTicket called");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Booking flight = bookingRepository.findByPnrNumber(pnrNumber);

		if (flight == null) {
			throw new BookingException("flight not found");
		}
		flight.setStatus("cancelled");
		Booking res = bookingRepository.save(flight);
		return modelMapper.map(res, BookingDto.class);
	}

	@Override
	public BookingDto findTicket(String pnrNumber) {
		log.info("findTicket called");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Booking flight = bookingRepository.findByPnrNumber(pnrNumber);
		if (flight == null) {
			throw new BookingException("flight not found");
		}
		return modelMapper.map(flight, BookingDto.class);
	}

	@Override
	public List<BookingDto> getUserTickets(String email) {
		
		log.info("getUserTickets called");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<Booking> booking = bookingRepository.findAllByEmail(email);
		if (booking == null) {
			throw new BookingException("flight not found");
		}
		List<BookingDto> tickets = booking.stream().map(ticket -> modelMapper.map(ticket, BookingDto.class))
				.collect(Collectors.toList());
		
		return tickets;
	}

}
