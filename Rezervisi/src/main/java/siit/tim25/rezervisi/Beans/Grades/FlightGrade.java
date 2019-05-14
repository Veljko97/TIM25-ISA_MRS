package siit.tim25.rezervisi.Beans.Grades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import siit.tim25.rezervisi.Beans.Flight;
import siit.tim25.rezervisi.Beans.users.StandardUser;

@Entity
public class FlightGrade {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private int score;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Flight flight;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private StandardUser user;

	public FlightGrade() {
		super();
		this.id = null;
		this.score = -1;
		this.flight = null;
		this.user = null;
	}
	
	public FlightGrade(Integer id, int score, Flight flight, StandardUser user) {
		super();
		this.id = id;
		this.score = score;
		this.flight = flight;
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

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public StandardUser getUser() {
		return user;
	}

	public void setUser(StandardUser user) {
		this.user = user;
	}
}
