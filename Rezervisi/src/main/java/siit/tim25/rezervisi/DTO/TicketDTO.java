package siit.tim25.rezervisi.DTO;

import java.util.Date;

import siit.tim25.rezervisi.Beans.Ticket;
import siit.tim25.rezervisi.Beans.TicketStatus;

public class TicketDTO {
	private String ticketPrice;
	
	private Integer ticketId;

	private String seat;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String passport;
		
	private TicketStatus status;
	
	private Integer idFlight;
	
	private String srcDestName;
	
	private String targetDestName;


	private Double totalPrice;
	
	private Integer airlineId;
	
	private Date created;
	
	private FlightDTO flight;
	
	public TicketDTO() {}
	
	public TicketDTO(Ticket t) {
		
		this.ticketPrice = String.valueOf(t.getTicketPrice());
		this.seat = t.getSeat();
		this.firstName = t.getFirstName();
		this.lastName = t.getLastName();
		this.email = t.getEmail();
		this.passport = t.getPassport();
		this.status = t.getStatus();
		this.idFlight = t.getFlight().getIdFlight();
		this.srcDestName = t.getFlight().getStartDestination().getDestinationName();
		this.targetDestName = t.getFlight().getFinalDestination().getDestinationName();
		this.created = t.getCreated();
		this.airlineId = t.getAirLine() != null ? t.getAirLine().getAirLineID() : null;
		this.ticketId = t.getIdTicket();
	}
	
	public TicketDTO(Ticket t, Double totalPrice) {
		
		this.ticketPrice = String.valueOf(t.getTicketPrice());
		this.seat = t.getSeat();
		this.firstName = t.getFirstName();
		this.lastName = t.getLastName();
		this.email = t.getEmail();
		this.passport = t.getPassport();
		this.status = t.getStatus();
		this.idFlight = t.getFlight().getIdFlight();
		this.srcDestName = t.getFlight().getStartDestination().getDestinationName();
		this.targetDestName = t.getFlight().getFinalDestination().getDestinationName();
		this.created = created;
		this.ticketId = t.getIdTicket();
		this.airlineId = t.getAirLine() != null ? t.getAirLine().getAirLineID() : null;
		this.totalPrice = totalPrice;
	}
	public TicketDTO(Integer airlineId, Integer ticketId, String ticketPrice, String seat, String firstName, String lastName, String email, String passport,
			TicketStatus status, Integer idFlight, String srcDestName, String targetDestName) {
		super();
		this.airlineId = airlineId;
		this.ticketId = ticketId;
		this.ticketPrice = ticketPrice;
		this.seat = seat;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.passport = passport;
		this.status = status;
		this.idFlight = idFlight;
		this.srcDestName = srcDestName;
		this.targetDestName = targetDestName;
	}

	public String getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getSeat() {
		return seat;
	}
	
	

	public Integer getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(Integer airlineId) {
		this.airlineId = airlineId;
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

	public String getEmail() {
		return email;
	}

	public Integer getTicketId() {
		return ticketId;
	}
	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public void setEmail(String email) {
		this.email = email;
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

	public Integer getIdFlight() {
		return idFlight;
	}

	public void setIdFlight(Integer idFlight) {
		this.idFlight = idFlight;
	}

	public String getSrcDestName() {
		return srcDestName;
	}

	public void setSrcDestName(String srcDestName) {
		this.srcDestName = srcDestName;
	}

	public String getTargetDestName() {
		return targetDestName;
	}

	public void setTargetDestName(String targetDestName) {
		this.targetDestName = targetDestName;
	}

	public FlightDTO getFlight() {
		return flight;
	}

	public void setFlight(FlightDTO flight) {
		this.flight = flight;
	}
	
}
