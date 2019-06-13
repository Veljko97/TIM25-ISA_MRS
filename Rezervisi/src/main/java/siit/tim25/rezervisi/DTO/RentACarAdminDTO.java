package siit.tim25.rezervisi.DTO;

import siit.tim25.rezervisi.Beans.users.RentACarAdmin;
import siit.tim25.rezervisi.security.model.Authority;
import siit.tim25.rezervisi.security.model.TokenState;

public class RentACarAdminDTO extends UserDTO{
	
	public RentACarAdminDTO(RentACarAdmin admin, TokenState token) {
		super(admin.getId(), admin.getUsername(), admin.getPhoneNumber(), admin.getCity(), admin.getFirstName(), admin.getLastName(),
				admin.getEmail(), admin.isEnabled(), admin.getRentACar().getRentACarID(), admin.isConfirmed(), admin.getImage(), token);
		for(Object au : admin.getAuthorities()) {
			this.setRole(((Authority) au).getName());
		}
	}
}
