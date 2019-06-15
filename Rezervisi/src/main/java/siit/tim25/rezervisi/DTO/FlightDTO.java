package siit.tim25.rezervisi.DTO;

import java.text.ParseException;
import java.util.Date;
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
	private Long takeOffDate;
	private Long landingDate;
	private String flightLength;
	private int numberOfStops;
	private int numberOfSeats;
	private String airplane;
	private Double ticketPrice;
	private Double averageGrade;
	private Integer idFlight;
	private String type;
	private String flightClass;
	private int airLineId;
	private List<String> seats;

	
	public FlightDTO() {
		super();
	}
	
	
	public FlightDTO(Integer idFlight, String startDestinationName, String finalDestinationName, Long takeOffDate, Long landingDate,
			String flightLength, int numberOfStops, int numberOfSeats, String flightClass, String type, String airplane, Double ticketPrice,
			Double averageGrade, int airLineId, List<String> seats) {
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
		this.averageGrade = averageGrade;
		this.type = type;
		this.flightClass = flightClass;
		this.airLineId = airLineId;
		this.seats = seats;
	}
	
	public FlightDTO(Flight fl) {
		super();
		this.idFlight = fl.getIdFlight();
		this.startDestinationName = fl.getStartDestination().getDestinationName();
		this.finalDestinationName = fl.getFinalDestination().getDestinationName();
		this.takeOffDate = fl.getTakeOffDate().getTime();
		this.landingDate = fl.getLandingDate().getTime();
		this.flightLength = fl.getFlightLength();
		this.numberOfStops = fl.getNumberOfStops();
		this.numberOfSeats = fl.getAirplane().getNumberOfSeats();
		this.airplane = fl.getAirplane().getName();
		this.ticketPrice = fl.getTicketPrice();
		this.averageGrade = fl.getAverageGrade();
		this.type = fl.getType().name();
		this.flightClass = fl.getFlightClass().name();
		this.airLineId = fl.getAirLine().getAirLineID();
		this.seats = fl.getTakenSeatNames();
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
	public Long getTakeOffDate() {
		return takeOffDate;
	}
	public void setTakeOffDate(Long takeOffDate) {
		this.takeOffDate = takeOffDate;
	}
	public int getAirLineId() {
		return airLineId;
	}
	public void setAirLineId(int airLineId) {
		this.airLineId = airLineId;
	}
	public Long getLandingDate() {
		return landingDate;
	}
	public void setLandingDate(Long landingDate) {
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

	public Double getAverageGrade() {
		return averageGrade;
	}
	public void setAverageGrade(Double averageGrade) {
		this.averageGrade = averageGrade;
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


	public List<String> getSeats() {
		return seats;
	}


	public void setSeats(List<String> seats) {
		this.seats = seats;
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
			    + ", ticketPrice=" + ticketPrice + ", averageGrade=" + averageGrade + "]";
	}
	
	public Flight convert(Set<Destination> destinations, List<AirPlane> airplanes) throws ParseException {
		Flight f = new Flight();
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
		
		f.setTakeOffDate(new Date(this.takeOffDate));
		f.setLandingDate(new Date(this.landingDate));
		f.setFlightLength(this.flightLength);
		f.setTicketPrice(this.getTicketPrice());
		f.setNumberOfStops(this.numberOfStops);
		f.setAverageGrade(this.averageGrade);
		f.setType(FlightType.valueOf(this.type));
		f.setFlightClass(FlightClass.valueOf(this.flightClass));
		return f;
	}
	
	public Flight convert(List<Destination> destinations, List<AirPlane> airplanes) throws ParseException {
		Flight f = new Flight();
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
		
		f.setTakeOffDate(new Date(this.takeOffDate));
		f.setLandingDate(new Date(this.landingDate));
		f.setFlightLength(this.flightLength);
		f.setTicketPrice(this.getTicketPrice());
		f.setNumberOfStops(this.numberOfStops);
		f.setAverageGrade(this.averageGrade);
		f.setType(FlightType.valueOf(this.type));
		f.setFlightClass(FlightClass.valueOf(this.flightClass));
		return f;
	}
}
