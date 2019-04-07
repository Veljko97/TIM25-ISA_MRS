package siit.tim25.rezervisi.DTO;

import siit.tim25.rezervisi.security.model.TokenState;

public class UserDTO {
	private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
    private Integer serviceId;
    private TokenState token;
	
    public UserDTO() {
		super();
		this.id = 0;
		this.username = "";
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.enabled = false;
		this.serviceId = -1;
		this.token = new TokenState();
	}
    
    public UserDTO(Integer id, String username, String firstName, String lastName, String email,
			boolean enabled, Integer serviceId, TokenState token) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.enabled = enabled;
		this.serviceId = serviceId;
		this.token = token;
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
}
