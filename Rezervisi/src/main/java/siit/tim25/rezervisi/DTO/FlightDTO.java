package siit.tim25.rezervisi.DTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import siit.tim25.rezervisi.Beans.AirPlane;
import siit.tim25.rezervisi.Beans.Destination;
import siit.tim25.rezervisi.Beans.Flight;
import siit.tim25.rezervisi.Beans.FlightClass;
import siit.tim25.rezervisi.Beans.FlightType;

public class FlightDTO {
	private String startDestinationName;
	private String finalDestinationName;
	private String takeOffDate;
	private String landingDate;
	private String flightLength;
	private int numberOfStops;
	private int numberOfSeats;
	private String airplane;
	private Double ticketPrice;
	private Double flightAverageGrade;
	private Integer idFlight;
	private String type;
	private String flightClass;

	
	public FlightDTO() {
		super();
	}
	
	
	public FlightDTO(Integer idFlight, String startDestinationName, String finalDestinationName, String takeOffDate, String landingDate,
			String flightLength, int numberOfStops, int numberOfSeats, String flightClass, String type, String airplane, Double ticketPrice,
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
		this.airplane = airplane;
		this.ticketPrice = ticketPrice;
		this.flightAverageGrade = flightAverageGrade;
		this.type = type;
		this.flightClass = flightClass;
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

	public String getAirplane() {
		return airplane;
	}

	public void setAirplane(String airplane) {
		this.airplane = airplane;
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

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getFlightClass() {
		return flightClass;
	}


	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}


	@Override
	public String toString() {
		return "NewFlight [startDestinationName=" + startDestinationName + ", finalDestinationName="
				+ finalDestinationName + ", takeOffDate=" + takeOffDate + ", landingDate=" + landingDate
				+ ", flightLength=" + flightLength + ", numberOfStops=" + numberOfStops
			    + ", ticketPrice=" + ticketPrice + ", flightAverageGrade=" + flightAverageGrade + "]";
	}
	
	public Flight convert(Set<Destination> destinations, List<AirPlane> airplanes) throws ParseException {
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
		
		for(AirPlane a: airplanes) {
			if(a.getName().equals(this.getAirplane())) {
				f.setAirplane(a);
			}
		}
		
		f.setTakeOffDate(format.parse(this.takeOffDate));
		f.setLandingDate(format.parse(this.landingDate));
		f.setFlightLength(this.flightLength);
		f.setTicketPrice(this.getTicketPrice());
		f.setNumberOfStops(this.numberOfStops);
		f.setFlightAverageGrade(this.flightAverageGrade);
		f.setType(FlightType.valueOf(this.type));
		f.setFlightClass(FlightClass.valueOf(this.flightClass));
		return f;
	}
}
