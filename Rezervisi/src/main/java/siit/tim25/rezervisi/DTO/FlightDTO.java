package siit.tim25.rezervisi.DTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import siit.tim25.rezervisi.Beans.Flight;

public class FlightDTO {
	private String startDestinationName;
	private String finalDestinationName;
	private String takeOffDate;
	private String landingDate;
	private String flightLength;
	private int numberOfStops;
	private String stopLocation;
	private Double ticketPrice;
	private Double flightAverageGrade;
	
	
	public FlightDTO() {
		super();
	}
	
	
	public FlightDTO(String startDestinationName, String finalDestinationName, String takeOffDate, String landingDate,
			String flightLength, int numberOfStops, String stopLocation, Double ticketPrice,
			Double flightAverageGrade) {
		super();
		this.startDestinationName = startDestinationName;
		this.finalDestinationName = finalDestinationName;
		this.takeOffDate = takeOffDate;
		this.landingDate = landingDate;
		this.flightLength = flightLength;
		this.numberOfStops = numberOfStops;
		this.stopLocation = stopLocation;
		this.ticketPrice = ticketPrice;
		this.flightAverageGrade = flightAverageGrade;
	}
	public String getStartDestinationName() {
		return startDestinationName;
	}
	public void setStartDestinationName(String startDestinationName) {
		this.startDestinationName = startDestinationName;
	}
	public String getFinalDestinationName() {
		return finalDestinationName;
	}
	public void setFinalDestinationName(String finalDestinationName) {
		this.finalDestinationName = finalDestinationName;
	}
	public String getTakeOffDate() {
		return takeOffDate;
	}
	public void setTakeOffDate(String takeOffDate) {
		this.takeOffDate = takeOffDate;
	}
	public String getLandingDate() {
		return landingDate;
	}
	public void setLandingDate(String landingDate) {
		this.landingDate = landingDate;
	}
	public String getFlightLength() {
		return flightLength;
	}
	public void setFlightLength(String flightLength) {
		this.flightLength = flightLength;
	}
	public int getNumberOfStops() {
		return numberOfStops;
	}
	public void setNumberOfStops(int numberOfStops) {
		this.numberOfStops = numberOfStops;
	}
	public String getStopLocation() {
		return stopLocation;
	}
	public void setStopLocation(String stopLocation) {
		this.stopLocation = stopLocation;
	}
	public Double getFlightTicket() {
		return ticketPrice;
	}
	public void setFlightTicket(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public Double getFlightAverageGrade() {
		return flightAverageGrade;
	}
	public void setFlightAverageGrade(Double flightAverageGrade) {
		this.flightAverageGrade = flightAverageGrade;
	}


	@Override
	public String toString() {
		return "NewFlight [startDestinationName=" + startDestinationName + ", finalDestinationName="
				+ finalDestinationName + ", takeOffDate=" + takeOffDate + ", landingDate=" + landingDate
				+ ", flightLength=" + flightLength + ", numberOfStops=" + numberOfStops + ", stopLocation="
				+ stopLocation + ", ticketPrice=" + ticketPrice + ", flightAverageGrade=" + flightAverageGrade + "]";
	}
	
	public Flight convert() throws ParseException {
		Flight f = new Flight();
		DateFormat format = new SimpleDateFormat("dd/mm/yyyy hh:mm");
		f.setTakeOffDate(format.parse(this.takeOffDate));
		f.setLandingDate(format.parse(this.landingDate));
		f.setFlightLength(this.flightLength);
		f.setNumberOfStops(this.numberOfStops);
		f.setFlightAverageGrade(this.flightAverageGrade);
		
		return f;
	}
}
