package siit.tim25.rezervisi.Beans.users;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Friends {
	@Id
	@GeneratedValue
	private Integer Id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private StandardUser sender;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private StandardUser receiver;
	
	@Column
	private Boolean confirmed;

	public Friends() {
		this.sender = null;
		this.receiver = null;
	}
	
	public Friends(StandardUser sender, StandardUser receiver) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.confirmed = false;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public StandardUser getSender() {
		return sender;
	}

	public void setSender(StandardUser sender) {
		this.sender = sender;
	}

	public StandardUser getReceiver() {
		return receiver;
	}

	public void setReceiver(StandardUser receiver) {
		this.receiver = receiver;
	}

	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}
}
