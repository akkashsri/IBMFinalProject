package com.flightApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FlightDto {
	
	private String flightId;

	private String airline;

	private String source;

	private String destination;

	private String startDate;

	private String endDate;
	
	private String startTime;

	private String endTime;

	private String instrumentUsed;

	private Integer businessClassSeats;

	private Integer nonBusinessClassSeats;

	private Double ticketCost;

	private Integer totalRows;

	private String meal;

	private String status;

}
