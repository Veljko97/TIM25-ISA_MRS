package siit.tim25.rezervisi.DTO;

import siit.tim25.rezervisi.Beans.Room;

public class RoomDTO {

	private Integer roomID;

	private String roomNumber;

	private String roomDescription;

	private String roomCapacity;
	
	private Double price;
	
	private Double averageGrade;
	
	public RoomDTO() {}
	
	public RoomDTO(Room room) {
		this.roomID = room.getRoomID();
		this.roomNumber = room.getRoomNumber();
		this.roomDescription = room.getRoomDescription();
		this.roomCapacity = room.getRoomCapacity();
		this.price = room.getPrice();
		this.averageGrade = room.getAverageGrade();
	}

	public RoomDTO(Integer roomID, String roomNumber, String roomDescription, String roomCapacity, Double price,
			Double averageGrade) {
		super();
		this.roomID = roomID;
		this.roomNumber = roomNumber;
		this.roomDescription = roomDescription;
		this.roomCapacity = roomCapacity;
		this.price = price;
		this.averageGrade = averageGrade;
	}

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

	public Double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(Double averageGrade) {
		this.averageGrade = averageGrade;
	}
	
}
