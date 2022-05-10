package com.flightCommonSvc.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightResponseModel {
	
	private String flightId;
	
	private String airLine;

	private String source;

	private String destination;

	private String startDate;

	private String endDate;
	
	private String startTime;

	private String endTime;

	private String instrumentUsed;

	private Integer businessClassSeats;

	private Integer nonBusinessClassSeats;

	private float ticketCost;

	private Integer totalRows;

	private String meal; 
	
	private String status;

}
