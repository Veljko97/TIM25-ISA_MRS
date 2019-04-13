package siit.tim25.rezervisi.Beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Column(nullable = false)
	private Date takeOffDate;
	
	@Column(nullable = false)
	private Date landingDate;
	
	@Column
	private String flightLength;
	
	@Column
	private int numberOfStops;
	
	@Column
	private int numberOfSeats;
	
	@Column
	private Double ticketPrice;
	
	@OneToMany
	private Set<Destination> stopLocations = new HashSet<Destination>();
	
	@OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
	private Set<Ticket> flightTickets = new HashSet<Ticket>();
	
	@Column
	private Double flightAverageGrade;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private AirLine airLine;
	
	
	public Flight() {
		super();
		this.flightAverageGrade = 0.0;
	}

	public Flight(Destination startDestination, Destination finalDestination, Date takeOffDate, Date landingDate,
			String flightLength, int numberOfStops, int numberOfSeats, Double ticketPrice, Set<Destination> stopLocations,
			Set<Ticket> flightTickets ,Double flightAverageGrade) {
		super();
		this.startDestination = startDestination;
		this.finalDestination = finalDestination;
		this.takeOffDate = takeOffDate;
		this.landingDate = landingDate;
		this.flightLength = flightLength;
		this.numberOfStops = numberOfStops;
		this.numberOfSeats = numberOfSeats;
		this.ticketPrice = ticketPrice;
		this.stopLocations = stopLocations;
		this.flightTickets = flightTickets;
		this.flightAverageGrade = flightAverageGrade;
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
	
	

	public Double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public AirLine getAirLine() {
		return airLine;
	}

	public void setAirLine(AirLine airLine) {
		this.airLine = airLine;
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
	public Set<Ticket> getFlightTicket() {
		return flightTickets;
	}
	public void setFlightTicket(Set<Ticket> flightTickets) {
		this.flightTickets = flightTickets;
	}
	
	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	
	

	@Override
	public String toString() {
		return "Flight [startDestination=" + startDestination + ", finalDestination=" + finalDestination
				+ ", takeOffDate=" + takeOffDate + ", landingDate=" + landingDate + ", flightLength=" + flightLength
				+ ", numberOfStops=" + numberOfStops + ", stopLocations=" + stopLocations + ", flightTicket="
				+ flightTickets + ", flightAverageGrade=" + flightAverageGrade + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
	    if (obj == this) return true;
	    if (!(obj instanceof Flight)) return false;
	    Flight o = (Flight) obj;
	    
		return o.idFlight == this.idFlight;
	}
	
	public FlightDTO convert() {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		format.format(this.takeOffDate);
		return new FlightDTO(this.idFlight, this.getStartDestination().getDestinationName(), this.getFinalDestination().getDestinationName(), format.format(this.takeOffDate), format.format(this.landingDate), this.getFlightLength(), this.getNumberOfStops(), this.getNumberOfSeats(), this.getTicketPrice(), this.getFlightAverageGrade());
	}
	
}
