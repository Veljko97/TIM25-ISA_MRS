package siit.tim25.rezervisi.Beans.users;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import siit.tim25.rezervisi.Beans.RentACar;
import siit.tim25.rezervisi.security.model.User;

@Entity
public class RentACarAdmin extends User{
	
	private static final long serialVersionUID = 9011662930453944266L;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private RentACar rentACar;
	
	public RentACarAdmin() {};
	
	public RentACarAdmin(RentACar rentACar) {
		this.rentACar = rentACar;
	}

	public RentACar getRentACar() {
		return rentACar;
	}

	public void setRentACar(RentACar rentACar) {
		this.rentACar = rentACar;
	}
}