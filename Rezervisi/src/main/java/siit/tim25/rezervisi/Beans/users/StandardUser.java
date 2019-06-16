package siit.tim25.rezervisi.Beans.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import siit.tim25.rezervisi.Beans.RoomReservation;
import siit.tim25.rezervisi.Beans.Ticket;
import siit.tim25.rezervisi.Beans.VehicleReservation;
import siit.tim25.rezervisi.Beans.Grades.AirLineGrade;
import siit.tim25.rezervisi.Beans.Grades.FlightGrade;
import siit.tim25.rezervisi.Beans.Grades.HotelGrade;
import siit.tim25.rezervisi.Beans.Grades.RentACarGrade;
import siit.tim25.rezervisi.Beans.Grades.RoomGrade;
import siit.tim25.rezervisi.Beans.Grades.VehicleGrade;
import siit.tim25.rezervisi.security.model.User;

@Entity
public class StandardUser extends User {

	private static final long serialVersionUID = -1537025436231150265L;

	@Column
	private Integer discauntPoints = 0;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<AirLineGrade> airLineGrades = new HashSet<AirLineGrade>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<HotelGrade> hotelGrades = new HashSet<HotelGrade>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<RoomGrade> roomGrades = new HashSet<RoomGrade>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<RentACarGrade> rentACarGrades = new HashSet<RentACarGrade>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<VehicleGrade> vehicleGrades = new HashSet<VehicleGrade>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<FlightGrade> flightGrades = new HashSet<FlightGrade>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Ticket> airLineTickets = new HashSet<Ticket>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<RoomReservation> roomReservation = new HashSet<RoomReservation>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<VehicleReservation> vehicleReservation = new HashSet<VehicleReservation>();
	
	@OneToMany(mappedBy = "sender", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Friends> sentRequest = new HashSet<Friends>();
	
	@OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Friends> receivedRequests = new HashSet<Friends>();

	public Set<AirLineGrade> getAirLineGrades() {
		return airLineGrades;
	}

	public void setAirLineGrades(Set<AirLineGrade> airLineGrades) {
		this.airLineGrades = airLineGrades;
	}

	public Set<HotelGrade> getHotelGrades() {
		return hotelGrades;
	}

	public void setHotelGrades(Set<HotelGrade> hotelGrades) {
		this.hotelGrades = hotelGrades;
	}

	public Set<RoomGrade> getRoomGrades() {
		return roomGrades;
	}

	public void setRoomGrades(Set<RoomGrade> roomGrades) {
		this.roomGrades = roomGrades;
	}

	public Set<RentACarGrade> getRentACarGrades() {
		return rentACarGrades;
	}

	public void setRentACarGrades(Set<RentACarGrade> rentACarGrades) {
		this.rentACarGrades = rentACarGrades;
	}

	public Set<VehicleGrade> getVehicleGrades() {
		return vehicleGrades;
	}

	public void setVehicleGrades(Set<VehicleGrade> vehicleGrades) {
		this.vehicleGrades = vehicleGrades;
	}

	public Set<Ticket> getAirLineTickets() {
		return airLineTickets;
	}

	public void setAirLineTickets(Set<Ticket> airLineTickets) {
		this.airLineTickets = airLineTickets;
	}

	public Set<RoomReservation> getRoomReservation() {
		return roomReservation;
	}

	public void setRoomReservation(Set<RoomReservation> roomReservation) {
		this.roomReservation = roomReservation;
	}

	public Set<VehicleReservation> getVehicleReservation() {
		return vehicleReservation;
	}

	public void setVehicleReservation(Set<VehicleReservation> vehicleReservation) {
		this.vehicleReservation = vehicleReservation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<Friends> getSentRequest() {
		return sentRequest;
	}

	public void setSentRequest(Set<Friends> sentRequest) {
		this.sentRequest = sentRequest;
	}

	public Set<Friends> getReceivedRequests() {
		return receivedRequests;
	}

	public void setReceivedRequests(Set<Friends> receivedRequests) {
		this.receivedRequests = receivedRequests;
	}

	public Set<FlightGrade> getFlightGrades() {
		return flightGrades;
	}

	public void setFlightGrades(Set<FlightGrade> flightGrades) {
		this.flightGrades = flightGrades;
	}

	public int getDiscauntPoints() {
		return discauntPoints;
	}

	public void setDiscauntPoints(int discauntPoints) {
		this.discauntPoints = discauntPoints;
	}
}
