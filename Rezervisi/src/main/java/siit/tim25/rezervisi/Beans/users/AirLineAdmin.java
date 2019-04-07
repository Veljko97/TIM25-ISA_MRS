package siit.tim25.rezervisi.Beans.users;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.security.model.User;

@Entity
public class AirLineAdmin extends User{
	
	private static final long serialVersionUID = -3568593022365172934L;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private AirLine airLine;
	
	public AirLineAdmin() {};
	
	public AirLineAdmin(AirLine airlne) {
		this.airLine = airlne;
	}

	public AirLine getAirLine() {
		return airLine;
	}

	public void setAirLine(AirLine airLine) {
		this.airLine = airLine;
	}

}
