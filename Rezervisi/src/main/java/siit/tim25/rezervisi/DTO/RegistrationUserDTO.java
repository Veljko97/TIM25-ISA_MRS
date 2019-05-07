package siit.tim25.rezervisi.DTO;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import siit.tim25.rezervisi.security.model.TokenState;
import siit.tim25.rezervisi.security.model.User;

public class RegistrationUserDTO extends UserDTO {
	
	@NotBlank
	private String password;
	
	@NotNull
	private boolean enabled;
	
	

	public RegistrationUserDTO(Integer id, String username, String firstName, String lastName, String email,
			boolean enabled, Integer serviceId, boolean confirmed, TokenState token) {
		super(id, username, firstName, lastName, email, enabled, serviceId, confirmed, "", token);
		// TODO Auto-generated constructor stub
	}

	public RegistrationUserDTO(User user, TokenState tokenState) {
		super(user, tokenState);
		// TODO Auto-generated constructor stub
	}

	public RegistrationUserDTO(User user) {
		super(user);
		// TODO Auto-generated constructor stub
	}

	public RegistrationUserDTO() {
		super();
	}

	public RegistrationUserDTO(String password, boolean enabled) {
		super();
		this.password = password;
		this.enabled = enabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
