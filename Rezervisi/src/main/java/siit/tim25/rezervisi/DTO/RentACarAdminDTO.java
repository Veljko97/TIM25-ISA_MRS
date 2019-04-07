package siit.tim25.rezervisi.DTO;

import siit.tim25.rezervisi.Beans.users.RentACarAdmin;
import siit.tim25.rezervisi.security.model.TokenState;

public class RentACarAdminDTO extends UserDTO{
	
	public RentACarAdminDTO(RentACarAdmin admin, TokenState token) {
		super(admin.getId(), admin.getUsername(), admin.getFirstName(), admin.getLastName(),
				admin.getEmail(), admin.isEnabled(), admin.getRentACar().getRentACarID(), token);
	}
}
