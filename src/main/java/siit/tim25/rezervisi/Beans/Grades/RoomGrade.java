package siit.tim25.rezervisi.Beans.Grades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import siit.tim25.rezervisi.Beans.Room;
import siit.tim25.rezervisi.Beans.users.StandardUser;

@Entity
public class RoomGrade {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private Double score;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Room room;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private StandardUser user;

	public RoomGrade() {
		super();
		this.id = null;
		this.score = null;
		this.room = null;
		this.user = null;
	}
	
	public RoomGrade(Integer id, Double score, Room room, StandardUser user) {
		super();
		this.id = id;
		this.score = score;
		this.room = room;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public StandardUser getUser() {
		return user;
	}

	public void setUser(StandardUser user) {
		this.user = user;
	}
}
