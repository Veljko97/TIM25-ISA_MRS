package siit.tim25.rezervisi.DTO;

import siit.tim25.rezervisi.Beans.users.HotelAdmin;
import siit.tim25.rezervisi.security.model.Authority;
import siit.tim25.rezervisi.security.model.TokenState;

public class HotelAdminDTO extends UserDTO{
	public HotelAdminDTO(HotelAdmin admin, TokenState token) {
		super(admin.getId(), admin.getUsername(),admin.getPhoneNumber(), admin.getCity(), admin.getFirstName(), admin.getLastName(),
				admin.getEmail(), admin.isEnabled(), admin.getHotel().getHotelID(), admin.isConfirmed(), admin.getImage(), token);
		for(Object au : admin.getAuthorities()) {
			this.setRole(((Authority) au).getName());
		}
	}
}
