package siit.tim25.rezervisi.DTO;

import siit.tim25.rezervisi.Beans.users.StandardUser;
import siit.tim25.rezervisi.security.model.TokenState;

public class FriendsDTO {
	
	private Integer id;	
	private StandardUserDTO other;
	
	public FriendsDTO() {
		this.id = -1;
		this.other = null;
	}
	
	public FriendsDTO(Integer id, StandardUser other) {
		super();
		this.id = id;
		this.other = new StandardUserDTO(other, new TokenState());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StandardUserDTO getOther() {
		return other;
	}

	public void setOther(StandardUserDTO other) {
		this.other = other;
	}
	
}
