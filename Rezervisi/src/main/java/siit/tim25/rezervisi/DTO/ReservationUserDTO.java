package siit.tim25.rezervisi.DTO;

import siit.tim25.rezervisi.Beans.ReservationUserType;
import siit.tim25.rezervisi.Beans.TicketStatus;

public class ReservationUserDTO {
	private String firstName;
	private String lastName;
	private String passport;
	private String email;
	private TicketStatus status;
	private ReservationUserType userType;
	private String seat;
	
	public ReservationUserDTO() {
		
	}

	public ReservationUserDTO(String firstName, String lastName, String passport, String email, TicketStatus status,
			ReservationUserType userType, String seat) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.passport = passport;
		this.email = email;
		this.status = status;
		this.userType = userType;
		this.seat = seat;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public ReservationUserType getUserType() {
		return userType;
	}

	public void setUserType(ReservationUserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "ReservationUserDTO [firstName=" + firstName + ", lastName=" + lastName + ", passport=" + passport
				+ ", email=" + email + ", status=" + status + ", userType=" + userType + ", seat=" + seat + "]";
	}
	
	
}
