package siit.tim25.rezervisi.DTO;

import siit.tim25.rezervisi.Beans.users.AirLineAdmin;
import siit.tim25.rezervisi.security.model.Authority;
import siit.tim25.rezervisi.security.model.TokenState;

public class AirLineAdminDTO extends UserDTO{
	
	public AirLineAdminDTO(AirLineAdmin admin, TokenState token) {
		super(admin.getId(), admin.getUsername(), admin.getFirstName(), admin.getLastName(),
				admin.getEmail(), admin.isEnabled(), admin.getAirLine().getAirLineID(),admin.isConfirmed(), token);
		for(Object au : admin.getAuthorities()) {
			this.setRole(((Authority) au).getName());
		}
	}
}
