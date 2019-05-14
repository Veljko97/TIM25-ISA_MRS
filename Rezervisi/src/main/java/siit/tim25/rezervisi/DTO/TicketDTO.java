package siit.tim25.rezervisi.DTO;

import siit.tim25.rezervisi.Beans.TicketStatus;

public class TicketDTO {
	private String ticketPrice;

	private String seat;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String passport;
		
	private TicketStatus status;
	
	private Integer idFlight;
	
	private String srcDestName;
	
	private String targetDestName;

	public TicketDTO(String ticketPrice, String seat, String firstName, String lastName, String email, String passport,
			TicketStatus status, Integer idFlight, String srcDestName, String targetDestName) {
		super();
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
