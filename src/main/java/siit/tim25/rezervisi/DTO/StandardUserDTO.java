package siit.tim25.rezervisi.DTO;

import siit.tim25.rezervisi.Beans.users.StandardUser;
import siit.tim25.rezervisi.security.model.Authority;
import siit.tim25.rezervisi.security.model.TokenState;

public class StandardUserDTO extends UserDTO{
	public StandardUserDTO(StandardUser user, TokenState token) {
		super(user.getId(), user.getUsername(), user.getPhoneNumber(), user.getCity(), user.getFirstName(), user.getLastName(),
				user.getEmail(), user.isEnabled(), -1, user.isConfirmed(), user.getImage(), token);
		for(Object au : user.getAuthorities()) {
			this.setRole(((Authority) au).getName());
		}
	}
}
