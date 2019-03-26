package siit.tim25.rezervisi.Beans;

import java.util.Date;

public class Flight {
	
	Destination startDestination;
	Destination finalDestination;
	Date takeOffDate;
	Date landingDate;
	String flightLength;
	int numberOfStops;
	String stopLocation;
	Ticket flightTicket;
	Double flightAverageGrade;
	
	
	public Flight() {
		super();
	}

	public Flight(Destination startDestination, Destination finalDestination, Date takeOffDate, Date landingDate,
			String flightLength, int numberOfStops, String stopLocation, Ticket flightTicket,Double flightAverageGrade) {
		super();
		this.startDestination = startDestination;
		this.finalDestination = finalDestination;
		this.takeOffDate = takeOffDate;
		this.landingDate = landingDate;
		this.flightLength = flightLength;
		this.numberOfStops = numberOfStops;
		this.stopLocation = stopLocation;
		this.flightTicket = flightTicket;
		this.flightAverageGrade = flightAverageGrade;
	}
	
	public Double getFlightAverageGrade() {
		return flightAverageGrade;
	}
	public void setFlightAverageGrade(Double flightAverageGrade) {
		this.flightAverageGrade = flightAverageGrade;
	}

	public Destination getStartDestination() {
		return startDestination;
	}
	public void setStartDestination(Destination startDestination) {
		this.startDestination = startDestination;
	}
	public Destination getFinalDestination() {
		return finalDestination;
	}
	public void setFinalDestination(Destination finalDestination) {
		this.finalDestination = finalDestination;
	}
	public Date getTakeOffDate() {
		return takeOffDate;
	}
	public void setTakeOffDate(Date takeOffDate) {
		this.takeOffDate = takeOffDate;
	}
	public Date getLandingDate() {
		return landingDate;
	}
	public void setLandingDate(Date landingDate) {
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
	public Ticket getFlightTicket() {
		return flightTicket;
	}
	public void setFlightTicket(Ticket flightTicket) {
		this.flightTicket = flightTicket;
	}
	@Override
	public String toString() {
		return "Flight [startDestination=" + startDestination + ", finalDestination=" + finalDestination
				+ ", takeOffDate=" + takeOffDate + ", landingDate=" + landingDate + ", flightLength=" + flightLength
				+ ", numberOfStops=" + numberOfStops + ", stopLocation=" + stopLocation + ", flightTicket="
				+ flightTicket + ", flightAverageGrade=" + flightAverageGrade + "]";
	}
	
	
	
}
