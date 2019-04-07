package siit.tim25.rezervisi.Beans.users;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import siit.tim25.rezervisi.Beans.Hotel;
import siit.tim25.rezervisi.security.model.User;

@Entity
public class HotelAdmin extends User{
	
	private static final long serialVersionUID = -6204484818539304167L;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Hotel hotel;
	
	public HotelAdmin() {};
	
	public HotelAdmin(Hotel hotel) {
		this.hotel = hotel;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}
