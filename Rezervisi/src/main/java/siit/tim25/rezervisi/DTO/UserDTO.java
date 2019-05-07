package siit.tim25.rezervisi.DTO;

import siit.tim25.rezervisi.security.model.Authority;
import siit.tim25.rezervisi.security.model.TokenState;
import siit.tim25.rezervisi.security.model.User;

public class UserDTO {
	private Integer id;
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private boolean enabled;
	private boolean confirmed;
	private Integer serviceId;
	private String role;
	private TokenState token;
	private String image;

	public UserDTO() {
		super();
		this.id = 0;
		this.username = "";
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.password = "";
		this.enabled = false;
		this.confirmed = false;
		this.serviceId = -1;
		this.role = "";
		this.token = new TokenState();
	}

	public UserDTO(Integer id, String username, String firstName, String lastName, String email, boolean enabled,
			Integer serviceId,boolean confirmed, String image, TokenState token) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.enabled = enabled;
		this.confirmed = confirmed;
		this.serviceId = serviceId;
		this.token = token;
		this.password = "";
		this.image = image;
	}

	public UserDTO(User user, TokenState tokenState) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.enabled = user.isEnabled();
		this.serviceId = -1;
		for(Object au : user.getAuthorities()) {
			this.role = ((Authority) au).getName();
		}
		this.confirmed = user.isConfirmed();
		this.token = tokenState;
	}
	
	public UserDTO(User user) {
		this(
			user.getId(),
			user.getUsername(),
			user.getFirstName(), 
			user.getLastName(),
			user.getEmail(),
			user.isEnabled(), 
			-1, 
			user.isConfirmed(),
			user.getImage(),
			new TokenState()
			);
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public TokenState getToken() {
		return token;
	}

	public void setToken(TokenState token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
