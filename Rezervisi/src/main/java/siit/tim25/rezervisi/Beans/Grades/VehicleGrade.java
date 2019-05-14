package siit.tim25.rezervisi.Beans.Grades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import siit.tim25.rezervisi.Beans.Vehicle;
import siit.tim25.rezervisi.Beans.users.StandardUser;

@Entity
public class VehicleGrade {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private int score;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Vehicle vehicle;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private StandardUser user;

	public VehicleGrade() {
		super();
		this.id = null;
		this.score = -1;
		this.vehicle = null;
		this.user = null;
	}
	
	public VehicleGrade(Integer id, int score, Vehicle vehicle, StandardUser user) {
		super();
		this.id = id;
		this.score = score;
		this.vehicle = vehicle;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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
	
	
}
