package com.flightApp.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "flights")
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, unique = true)
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

	private Integer totalSeats;

	private String meal;

	private String status;

}
