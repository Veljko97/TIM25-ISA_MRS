package siit.tim25.rezervisi.DTO;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import siit.tim25.rezervisi.Beans.AirPlane;
import siit.tim25.rezervisi.Beans.Destination;
import siit.tim25.rezervisi.Beans.Flight;
import siit.tim25.rezervisi.Beans.FlightType;

public class FlightDTO {
	private String startDestinationName;
	private String finalDestinationName;
	private Long takeOffDate;
	private Long landingDate;
	private String flightLength;
	private int numberOfStops;
	private int numberOfEconomyClassSeats;
	private int numberOfFirstClassSeats;
	private int numberOfBusinessClassSeats;
	private String airplane;
	private String additionalServices;
	private Double firstClassPrice;
	private Double businessClassPrice;
	private Double economyClassPrice;
	private Double averageGrade;
	private Integer idFlight;
	private String type;
	private int airLineId;
	private List<String> seats;

	
	public FlightDTO() {
		super();
	}
	
	
	public FlightDTO(Integer idFlight, String startDestinationName, String finalDestinationName, Long takeOffDate, Long landingDate,
			String flightLength, int numberOfStops, int numberOfEconomyClassSeats, int numberOfBusinessClassSeats, int numberOfFirstClassSeats, String type, String airplane,  Double firstClassPrice, Double businessClassPrice,
			Double economyClassPrice, Double averageGrade, int airLineId, List<String> seats, String additionalServices) {
		super();
		this.idFlight = idFlight;
		this.startDestinationName = startDestinationName;
		this.finalDestinationName = finalDestinationName;
		this.takeOffDate = takeOffDate;
		this.landingDate = landingDate;
		this.flightLength = flightLength;
		this.numberOfStops = numberOfStops;
		this.numberOfEconomyClassSeats = numberOfEconomyClassSeats;
		this.numberOfBusinessClassSeats = numberOfBusinessClassSeats;
		this.numberOfFirstClassSeats = numberOfFirstClassSeats;
		this.airplane = airplane;
		this.firstClassPrice = firstClassPrice;
		this.businessClassPrice = businessClassPrice;
		this.economyClassPrice = economyClassPrice;		this.averageGrade = averageGrade;
		this.type = type;
		this.airLineId = airLineId;
		this.seats = seats;
		this.additionalServices = additionalServices;
	}


	public FlightDTO(Flight fl) {
		super();
		this.idFlight = fl.getIdFlight();
		this.startDestinationName = fl.getStartDestination().getDestinationName();
		this.finalDestinationName = fl.getFinalDestination().getDestinationName();
		this.takeOffDate = fl.getTakeOffDate().getTime();
		this.landingDate = fl.getLandingDate().getTime();
		this.flightLength = fl.getFlightLength();
		this.numberOfStops = fl.getStopLocations().size();
		this.numberOfBusinessClassSeats = fl.getAirplane().getNumberOfBusinessClassSeats();
		this.numberOfEconomyClassSeats = fl.getAirplane().getNumberOfEconomyClassSeats();
		this.numberOfFirstClassSeats = fl.getAirplane().getNumberOfFirstClassSeats();
		this.airplane = fl.getAirplane().getName();
		this.firstClassPrice = fl.getFirstClassPrice();
		this.businessClassPrice = fl.getBusinessClassPrice();
		this.economyClassPrice = fl.getEconomyClassPrice();
		this.averageGrade = fl.getAverageGrade();
		this.type = fl.getType().name();
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

	public String getAirplane() {
		return airplane;
	}

	public void setAirplane(String airplane) {
		this.airplane = airplane;
	}


	public Double getFirstClassPrice() {
		return firstClassPrice;
	}



	public void setFirstClassPrice(Double firstClassPrice) {
		this.firstClassPrice = firstClassPrice;
	}



	public Double getBusinessClassPrice() {
		return businessClassPrice;
	}



	public void setBusinessClassPrice(Double businessClassPrice) {
		this.businessClassPrice = businessClassPrice;
	}



	public Double getEconomyClassPrice() {
		return economyClassPrice;
	}



	public void setEconomyClassPrice(Double economyClassPrice) {
		this.economyClassPrice = economyClassPrice;
	}



	public String getAdditionalServices() {
		return additionalServices;
	}


	public void setAdditionalServices(String additionalServices) {
		this.additionalServices = additionalServices;
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
	

	public int getNumberOfEconomyClassSeats() {
		return numberOfEconomyClassSeats;
	}


	public void setNumberOfEconomyClassSeats(int numberOfEconomyClassSeats) {
		this.numberOfEconomyClassSeats = numberOfEconomyClassSeats;
	}


	public int getNumberOfFirstClassSeats() {
		return numberOfFirstClassSeats;
	}


	public void setNumberOfFirstClassSeats(int numberOfFirstClassSeats) {
		this.numberOfFirstClassSeats = numberOfFirstClassSeats;
	}


	public int getNumberOfBusinessClassSeats() {
		return numberOfBusinessClassSeats;
	}


	public void setNumberOfBusinessClassSeats(int numberOfBusinessClassSeats) {
		this.numberOfBusinessClassSeats = numberOfBusinessClassSeats;
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


	
	@Override
	public String toString() {
		return "FlightDTO [startDestinationName=" + startDestinationName + ", finalDestinationName="
				+ finalDestinationName + ", takeOffDate=" + takeOffDate + ", landingDate=" + landingDate
				+ ", flightLength=" + flightLength + ", numberOfStops=" + numberOfStops + ", numberOfEconomyClassSeats="
				+ numberOfEconomyClassSeats + ", numberOfFirstClassSeats=" + numberOfFirstClassSeats
				+ ", numberOfBusinessClassSeats=" + numberOfBusinessClassSeats + ", airplane=" + airplane
				+ ", additionalServices=" + additionalServices + ", firstClassPrice=" + firstClassPrice
				+ ", businessClassPrice=" + businessClassPrice + ", economyClassPrice=" + economyClassPrice
				+ ", averageGrade=" + averageGrade + ", idFlight=" + idFlight + ", type=" + type + ", airLineId="
				+ airLineId + ", seats=" + seats + "]";
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
		f.setFirstClassPrice(this.firstClassPrice);
		f.setBusinessClassPrice(this.businessClassPrice);
		f.setEconomyClassPrice(this.economyClassPrice);
		f.setAverageGrade(this.averageGrade);
		f.setType(FlightType.valueOf(this.type));
		f.setAdditionalServices(this.additionalServices);
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
		f.setFirstClassPrice(this.firstClassPrice);
		f.setBusinessClassPrice(this.businessClassPrice);
		f.setEconomyClassPrice(this.economyClassPrice);
		f.setAverageGrade(this.averageGrade);
		f.setType(FlightType.valueOf(this.type));
		f.setAdditionalServices(this.additionalServices);
		return f;
	}
}
