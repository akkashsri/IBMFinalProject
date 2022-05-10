package com.flightApp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "bookedflights")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String pnrNumber;
	
	private String flightName;

	private String flightId;

	private LocalDate date;
	
	private LocalDate bookingDate;
	
	private String startTime;

	private String endTime;

	private String source;

	private String destination;

	private String name;

	private String email;

	private Integer totalSeats;

	private String mealType;
	
	private String status;

	@ElementCollection
	private List<Passengers> passengers = new ArrayList<>();

}
