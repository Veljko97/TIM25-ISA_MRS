package siit.tim25.rezervisi.DTO;

import siit.tim25.rezervisi.Beans.users.AirLineAdmin;
import siit.tim25.rezervisi.security.model.Authority;
import siit.tim25.rezervisi.security.model.TokenState;

public class AirLineAdminDTO extends UserDTO{
	
	public AirLineAdminDTO(AirLineAdmin admin, TokenState token) {
		super(admin.getId(), admin.getUsername(), admin.getPhoneNumber(), admin.getCity(), admin.getFirstName(), admin.getLastName(),
				admin.getEmail(), admin.isEnabled(), admin.getAirLine().getAirLineID(),admin.isConfirmed(), admin.getImage(), token);
		for(Object au : admin.getAuthorities()) {
			this.setRole(((Authority) au).getName());
		}
	}

	@Override
	public String toString() {
		return "AirLineAdminDTO [getId()=" + getId() + ", getUsername()=" + getUsername() + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + ", getEmail()=" + getEmail() + ", isEnabled()="
				+ isEnabled() + ", getServiceId()=" + getServiceId() + ", getToken()=" + getToken() + ", getRole()="
				+ getRole() + ", isConfirmed()=" + isConfirmed() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
