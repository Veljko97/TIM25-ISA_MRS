package siit.tim25.rezervisi.Beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import siit.tim25.rezervisi.Beans.users.StandardUser;
import siit.tim25.rezervisi.DTO.TicketDTO;


@Entity
public class Ticket {
	@Id
	@GeneratedValue
	private Integer idTicket;
	
	@Column
	private Double ticketPrice;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private AirLine airLine;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private StandardUser user;

	@Column
	private String seat;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String email;
	
	@Column
	private String passport;
	
	@Column
	private FlightClass flightClass;
	
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Column
	private TicketStatus status;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Flight flight;
	
	
	public Ticket() {
		super();
	}
	
	
	
	public Ticket(Double ticketPrice, String seat, String firstName, String lastName, String passport,
			TicketStatus status, Flight flight, String email, AirLine a, FlightClass flightClass) {
		super();
		this.ticketPrice = ticketPrice;
		this.seat = seat;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passport = passport;
		this.status = status;
		this.flight = flight;
		this.email = email;
		this.created = new Date();
		this.airLine = a;
		this.flightClass = flightClass;
	}


	public Double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	public Integer getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(Integer idTicket) {
		this.idTicket = idTicket;
	}
	
	

	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public AirLine getAirLine() {
		return airLine;
	}



	public void setAirLine(AirLine airLine) {
		this.airLine = airLine;
	}



	public StandardUser getUser() {
		return user;
	}



	public void setUser(StandardUser user) {
		this.user = user;
	}



	public String getSeat() {
		return seat;
	}



	public void setSeat(String seat) {
		this.seat = seat;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getPassport() {
		return passport;
	}



	public void setPassport(String passport) {
		this.passport = passport;
	}



	public TicketStatus getStatus() {
		return status;
	}



	public void setStatus(TicketStatus status) {
		this.status = status;
	}



	public Date getCreated() {
		return created;
	}



	public void setCreated(Date created) {
		this.created = created;
	}



	@Override
	public String toString() {
		return "Ticket [ticketPrice=" + ticketPrice + ", flight=" + flight + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
	    if (obj == this) return true;
	    if (!(obj instanceof Ticket)) return false;
	    Ticket o = (Ticket) obj;
	    
		return o.idTicket == this.idTicket;
	}
	
	

	public TicketDTO convert() {
		Integer airLineId = this.airLine != null ? this.airLine.getAirLineID() : null;
		return new TicketDTO(airLineId, this.idTicket, String.valueOf(ticketPrice), seat, firstName, lastName, email, passport, status, this.getFlight().getIdFlight(), this.getFlight().getStartDestination().getDestinationName(), this.getFlight().getFinalDestination().getDestinationName());
	}
	
	
	
}
