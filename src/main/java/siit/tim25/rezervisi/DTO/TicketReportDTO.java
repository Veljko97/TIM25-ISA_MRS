package siit.tim25.rezervisi.DTO;

import java.util.Date;

import siit.tim25.rezervisi.Beans.Ticket;
import siit.tim25.rezervisi.Beans.TicketStatus;

public class TicketReportDTO {
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
	
	private Long created;
	
	public TicketReportDTO(Ticket t, Double totalPrice, Integer created) {
		
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
		this.created = new Long(created);
		this.ticketId = t.getIdTicket();
		this.airlineId = t.getAirLine() != null ? t.getAirLine().getAirLineID() : null;
		this.totalPrice = totalPrice;
	}
	
	public TicketReportDTO(Ticket t, Double totalPrice, Date created) {
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
		this.created = created.getTime();
		this.ticketId = t.getIdTicket();
		this.airlineId = t.getAirLine() != null ? t.getAirLine().getAirLineID() : null;
		this.totalPrice = totalPrice;
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
	

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
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
	
	
}
