package com.flightApp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.flightApp.dto.FlightDto;
import com.flightApp.exception.FlightException;
import com.flightApp.model.Flight;
import com.flightApp.repo.FlightRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {
	
	private final FlightRepository flightRepository;
	private final ModelMapper modelMapper;

	@Override
	public FlightDto addFlight(FlightDto flightDto) {
		
		log.info("addFlight called");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Flight flight = modelMapper.map(flightDto, Flight.class);
		Integer totalSeats = flightDto.getBusinessClassSeats() + flightDto.getNonBusinessClassSeats();
		flight.setStatus("running");
		flight.setTotalSeats(totalSeats);
		Flight res = flightRepository.save(flight);
		return modelMapper.map(res, FlightDto.class);
		
	}

	@Override
	public FlightDto blockFlight(String flightId) {
		
		log.info("blockFlight called");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Flight flight = flightRepository.findByFlightId(flightId);
		if(flight == null) {
			throw new FlightException("not flight found");
		}
		if (flight.getStatus().equals("running")) {
			flight.setStatus("blocked");

		} else {
			flight.setStatus("running");
		}
		Flight res = flightRepository.save(flight);
		return modelMapper.map(res, FlightDto.class);
		
	}

	@Override
	public List<FlightDto> searchFlights(String source, String destination, String departureDate, String seatType) {
		
		log.info("searchFlights called");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<FlightDto> flights = new ArrayList<>();
		LocalDate startDate = LocalDate.parse(departureDate);
		List<Flight> res = flightRepository.findByCriteria(source, destination, startDate);
		if(res == null) {
			throw new FlightException("no flight available");
		}
		flights = res.stream().map(flight -> modelMapper.map(flight, FlightDto.class))
				.filter(f -> seatType.equalsIgnoreCase("economy") ? f.getNonBusinessClassSeats() > 0
						: f.getBusinessClassSeats() > 0)
				.collect(Collectors.toList());
		
		return flights;
		
	}

	@Override
	public List<FlightDto> getAllFlights() {
		
		log.info("getAllFlights called");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<Flight> flights = flightRepository.findAll();
		if(flights == null) {
			throw new FlightException("no flight available");
		}
		List<FlightDto> res = new ArrayList<>();
		for (Flight flight : flights) {
			res.add(modelMapper.map(flight, FlightDto.class));
		}
		return res;
		
	}

	@Override
	public FlightDto updateFlight(String flightId, int seats, String seatType) {
		
		log.info("updateFlight called");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Flight flight = flightRepository.findByFlightId(flightId);
		if(flight == null) {
			throw new FlightException("no flight found");
		}
		if (seatType.equalsIgnoreCase("business")) {
			flight.setBusinessClassSeats(flight.getBusinessClassSeats() - seats);
			flight.setTotalSeats(flight.getBusinessClassSeats() + flight.getNonBusinessClassSeats());

		} else {
			flight.setNonBusinessClassSeats(flight.getNonBusinessClassSeats() - seats);
			flight.setTotalSeats(flight.getBusinessClassSeats() + flight.getNonBusinessClassSeats());
		}
		Flight res = flightRepository.save(flight);
		return modelMapper.map(res, FlightDto.class);
	}
	

}
