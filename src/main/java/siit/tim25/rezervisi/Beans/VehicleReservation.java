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

import siit.tim25.rezervisi.Beans.users.StandardUser;

@Entity
public class VehicleReservation {
	
	@Id
	@GeneratedValue
	private Integer Id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Vehicle vehicle;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private StandardUser user;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date reservationStart;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date reservationEnd;
	
	@Column
	private Double price;
	
	@Column
	private TicketStatus status;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date made;
	
	public VehicleReservation(Vehicle vehicle, StandardUser user, Date reservationStart,
			Date reservationEnd, Double price, TicketStatus status, Date made) {
		super();
		this.vehicle = vehicle;
		this.user = user;
		this.reservationStart = reservationStart;
		this.reservationEnd = reservationEnd;
		this.price = price;
		this.status = status;
		this.made = made;
	}

	public VehicleReservation() {
		this.made = new Date();
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public StandardUser getUser() {
		return user;
	}

	public void setUser(StandardUser user) {
		this.user = user;
	}

	public Date getReservationStart() {
		return reservationStart;
	}

	public void setReservationStart(Date reservationStart) {
		this.reservationStart = reservationStart;
	}

	public Date getReservationEnd() {
		return reservationEnd;
	}

	public void setReservationEnd(Date reservationEnd) {
		this.reservationEnd = reservationEnd;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public Date getMade() {
		return made;
	}

	public void setMade(Date made) {
		this.made = made;
	}

	

}
