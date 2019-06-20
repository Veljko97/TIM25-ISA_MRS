package siit.tim25.rezervisi.Beans.Grades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import siit.tim25.rezervisi.Beans.Hotel;
import siit.tim25.rezervisi.Beans.users.StandardUser;

@Entity
public class HotelGrade {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private Double score;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Hotel hotel;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private StandardUser user;

	public HotelGrade() {
		super();
		this.id = null;
		this.score = null;
		this.hotel = null;
		this.user = null;
	}
	
	public HotelGrade(Integer id, Double score, Hotel hotel, StandardUser user) {
		super();
		this.id = id;
		this.score = score;
		this.hotel = hotel;
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

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public StandardUser getUser() {
		return user;
	}

	public void setUser(StandardUser user) {
		this.user = user;
	}
}
