package siit.tim25.rezervisi.Beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	private Date reservationStart;
	
	@Column
	private Date reservationEnd;
	
	@Column
	private Double price;
}
