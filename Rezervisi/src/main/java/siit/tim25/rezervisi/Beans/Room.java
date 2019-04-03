package siit.tim25.rezervisi.Beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Room {
	
	
	@Id
	@GeneratedValue
	private Integer roomID;
	
	@Column(name = "roomNumber",nullable = false)
	private String roomNumber;
	
	@Column (name = "roomDescription")
	private String roomDescription;
	
	@Column (name = "roomCapacity")
	private String roomCapacity;
	
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<HotelRoom> hotelRoom;

	
	public Integer getRoomID() {
		return roomID;
	}

	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public String getRoomCapacity() {
		return roomCapacity;
	}

	public void setRoomCapacity(String roomCapacity) {
		this.roomCapacity = roomCapacity;
	}

	public Set<HotelRoom> getHotelRoom(String id) {
		return hotelRoom;
	}

	public void setHotelRoom(Set<HotelRoom> hotelRoom) {
		this.hotelRoom = hotelRoom;
	}
	
	public Room(Integer roomID, String roomNumber, String roomDescription, String roomCapacity,
			Set<HotelRoom> hotelRoom) {
		super();
		this.roomID = roomID;
		this.roomNumber = roomNumber;
		this.roomDescription = roomDescription;
		this.roomCapacity = roomCapacity;
		this.hotelRoom = hotelRoom;
	}

	public Room() {
		super();
	} 
	
	
	
	
	
	
}
