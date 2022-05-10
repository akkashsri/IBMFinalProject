package com.flightApp.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightDto {

	private String flightId;

	private String airLine;

	private String source;

	private String destination;

	private LocalDate startDate;

	private LocalDate endDate;

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
