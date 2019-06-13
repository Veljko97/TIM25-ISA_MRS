package siit.tim25.rezervisi.Beans.Grades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import siit.tim25.rezervisi.Beans.RentACar;
import siit.tim25.rezervisi.Beans.users.StandardUser;

@Entity
public class RentACarGrade {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private Double score;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private RentACar rentACar;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private StandardUser user;

	public RentACarGrade() {
		super();
		this.id = null;
		this.score = null;
		this.rentACar = null;
		this.user = null;
	}
	
	public RentACarGrade(Integer id, Double score, RentACar rentACar, StandardUser user) {
		super();
		this.id = id;
		this.score = score;
		this.rentACar = rentACar;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public RentACar getRentACar() {
		return rentACar;
	}

	public void setRentACar(RentACar rentACar) {
		this.rentACar = rentACar;
	}

	public StandardUser getUser() {
		return user;
	}

	public void setUser(StandardUser user) {
		this.user = user;
	}
}
