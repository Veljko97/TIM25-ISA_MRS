package siit.tim25.rezervisi.Beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Column (name = "price")
	private Double price;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Hotel hotel;

	
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
	
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Room(Integer roomID, String roomNumber, String roomDescription, String roomCapacity,
			Hotel hotel) {
		super();
		this.roomID = roomID;
		this.roomNumber = roomNumber;
		this.roomDescription = roomDescription;
		this.roomCapacity = roomCapacity;
		this.hotel = hotel;
	}

	public Room() {
		super();
	} 
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
	    if (obj == this) return true;
	    if (!(obj instanceof Room)) return false;
	    Room o = (Room) obj;
	    
		return o.roomID == this.roomID;
	}

	@Override
	public String toString() {
		return "Room [roomID=" + roomID + ", roomNumber=" + roomNumber + ", roomDescription=" + roomDescription
				+ ", roomCapacity=" + roomCapacity + ", price=" + price + ", hotel=" + hotel + "]";
	}
	
	
	
	
}
