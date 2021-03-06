package siit.tim25.rezervisi.Beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import siit.tim25.rezervisi.Beans.Grades.FlightGrade;
import siit.tim25.rezervisi.DTO.FlightDTO;

@Entity
public class Flight {
	
	@Id
	@GeneratedValue
	private Integer idFlight;
	
	@ManyToOne
	private Destination startDestination;
	
	@ManyToOne
	private Destination finalDestination;
	
	@ManyToOne
	private AirPlane airplane;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date takeOffDate;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date landingDate;
	
	@Column
	private String flightLength;
	
	@Column
	private FlightType type;
	
	@Column
	private Double firstClassPrice;
	
	@Column
	private Double businessClassPrice;
	
	@Column
	private Double economyClassPrice;
	
	@OneToMany
	private Set<Destination> stopLocations = new HashSet<Destination>();
	
	@OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Ticket> flightTickets = new HashSet<Ticket>();
	
	@OneToMany(mappedBy = "flight", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<FlightGrade> grades = new HashSet<FlightGrade>();
	
	@Column
	private Double averageGrade;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private AirLine airLine;
	
	@Column
	private String additionalServices;
	
	
	public Flight() {
		super();
		this.averageGrade = 0.0;
	}
	
	

	public Flight(Destination startDestination, Destination finalDestination, AirPlane airplane, Date takeOffDate,
			Date landingDate, String flightLength, FlightType type, Double firstClassPrice, Double businessClassPrice,
			Double economyClassPrice, Set<Destination> stopLocations, Set<Ticket> flightTickets,
			Set<FlightGrade> grades, Double averageGrade, AirLine airLine, String additionalServices) {
		super();
		this.startDestination = startDestination;
		this.finalDestination = finalDestination;
		this.airplane = airplane;
		this.takeOffDate = takeOffDate;
		this.landingDate = landingDate;
		this.flightLength = flightLength;
		this.type = type;
		this.firstClassPrice = firstClassPrice;
		this.businessClassPrice = businessClassPrice;
		this.economyClassPrice = economyClassPrice;
		this.additionalServices = additionalServices;
		this.stopLocations = stopLocations;
		this.flightTickets = flightTickets;
		this.grades = grades;
		this.averageGrade = averageGrade;
		this.airLine = airLine;
	}

	public Integer getIdFlight() {
		return idFlight;
	}

	public void setIdFlight(Integer idFlight) {
		this.idFlight = idFlight;
	}

	public Set<Destination> getStopLocations() {
		return stopLocations;
	}

	public void setStopLocations(Set<Destination> stopLocations) {
		this.stopLocations = stopLocations;
	}

	public Set<Ticket> getFlightTickets() {
		return flightTickets;
	}

	public void setFlightTickets(Set<Ticket> flightTickets) {
		this.flightTickets = flightTickets;
	}

	public Double getFirstClassPrice() {
		return firstClassPrice;
	}

	public void setFirstClassPrice(Double firstClassPrice) {
		this.firstClassPrice = firstClassPrice;
	}

	public String getAdditionalServices() {
		return additionalServices;
	}



	public void setAdditionalServices(String additionalServices) {
		this.additionalServices = additionalServices;
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

	public AirLine getAirLine() {
		return airLine;
	}

	public void setAirLine(AirLine airLine) {
		this.airLine = airLine;
	}

	public Double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(Double averageGrade) {
		this.averageGrade = averageGrade;
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
	
	public AirPlane getAirplane() {
		return airplane;
	}

	public void setAirplane(AirPlane airplane) {
		this.airplane = airplane;
	}

	public FlightType getType() {
		return type;
	}

	public void setType(FlightType type) {
		this.type = type;
	}

	public Set<FlightGrade> getGrades() {
		return grades;
	}

	public void setGrades(Set<FlightGrade> grades) {
		this.grades = grades;
	}

	@Override
	public String toString() {
		return "Flight [startDestination=" + startDestination + ", finalDestination=" + finalDestination
				+ ", takeOffDate=" + takeOffDate + ", landingDate=" + landingDate + ", flightLength=" + flightLength
				 + ", stopLocations=" + stopLocations + ", flightAverageGrade=" + averageGrade + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
	    if (obj == this) return true;
	    if (!(obj instanceof Flight)) return false;
	    Flight o = (Flight) obj;
	    
		return o.idFlight == this.idFlight;
	}
	
	public List<String> getTakenSeatNames() {
		List<String> seats = new ArrayList<String>();

		for(Ticket t: this.flightTickets) {
			seats.add(t.getSeat());
		}
		return seats;
	}
	
	public double getPrice(FlightClass fClass) {
		double price = 0;
		switch(fClass) {
		case FIRST:
			price = this.getFirstClassPrice();
			break;
		case ECONOMY:
			price = this.getEconomyClassPrice();
			break;
		case BUSINESS:
			price = this.getBusinessClassPrice();
			break;
		default:
			price = this.getEconomyClassPrice();
		}
		return price;
	}
	
	public FlightDTO convert() {
		return new FlightDTO(this.idFlight, this.getStartDestination().getDestinationName(), this.getFinalDestination().getDestinationName(), this.takeOffDate.getTime(), this.landingDate.getTime(), this.getFlightLength(), this.stopLocations.size(), this.getAirplane().getNumberOfEconomyClassSeats(), this.getAirplane().getNumberOfBusinessClassSeats(), this.getAirplane().getNumberOfFirstClassSeats(), this.type.toString(), this.getAirplane().getName(), this.getFirstClassPrice(), this.getBusinessClassPrice(), this.getEconomyClassPrice(), this.getAverageGrade(), this.airLine.getAirLineID(), this.getTakenSeatNames(), this.additionalServices);
	}
	
}
