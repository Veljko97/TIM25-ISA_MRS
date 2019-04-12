package siit.tim25.rezervisi.DTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import siit.tim25.rezervisi.Beans.Destination;
import siit.tim25.rezervisi.Beans.Flight;

public class FlightDTO {
	private String startDestinationName;
	private String finalDestinationName;
	private String takeOffDate;
	private String landingDate;
	private String flightLength;
	private int numberOfStops;
	private int numberOfSeats;
	private Double ticketPrice;
	private Double flightAverageGrade;
	private Integer idFlight;

	
	public FlightDTO() {
		super();
	}
	
	
	public FlightDTO(Integer idFlight, String startDestinationName, String finalDestinationName, String takeOffDate, String landingDate,
			String flightLength, int numberOfStops, int numberOfSeats, Double ticketPrice,
			Double flightAverageGrade) {
		super();
		this.idFlight = idFlight;
		this.startDestinationName = startDestinationName;
		this.finalDestinationName = finalDestinationName;
		this.takeOffDate = takeOffDate;
		this.landingDate = landingDate;
		this.flightLength = flightLength;
		this.numberOfStops = numberOfStops;
		this.numberOfSeats = numberOfSeats;
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
	
	public Double getTicketPrice() {
		return ticketPrice;
	}

	
	

	public int getNumberOfSeats() {
		return numberOfSeats;
	}


	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}


	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Integer getIdFlight() {
		return idFlight;
	}

	public void setIdFlight(Integer idFlight) {
		this.idFlight = idFlight;
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
				+ ", flightLength=" + flightLength + ", numberOfStops=" + numberOfStops
			    + ", ticketPrice=" + ticketPrice + ", flightAverageGrade=" + flightAverageGrade + "]";
	}
	
	public Flight convert(Set<Destination> destinations) throws ParseException {
		Flight f = new Flight();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		for(Destination d: destinations) {
			if (d.getDestinationName().equals(this.getStartDestinationName())) {
				f.setStartDestination(d);
			}
			if(d.getDestinationName().equals(this.getFinalDestinationName())) {
				f.setFinalDestination(d);
			}
		}
		
		f.setTakeOffDate(format.parse(this.takeOffDate));
		f.setLandingDate(format.parse(this.landingDate));
		f.setFlightLength(this.flightLength);
		f.setTicketPrice(this.getTicketPrice());
		f.setNumberOfStops(this.numberOfStops);
		f.setFlightAverageGrade(this.flightAverageGrade);
		f.setNumberOfSeats(this.numberOfSeats);
		
		return f;
	}
}
