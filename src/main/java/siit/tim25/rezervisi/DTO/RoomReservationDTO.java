package siit.tim25.rezervisi.DTO;

import java.util.Date;

import siit.tim25.rezervisi.Beans.RoomReservation;

public class RoomReservationDTO {
	private String hotelName;
	
	private String roomNumber;

	private Date reservationStart;
	
	private Date reservationEnd;
	
	private Integer hotelId;
	
	private Integer roomId;
	
	private Double price;
	
	private Integer id;
	
	public RoomReservationDTO(RoomReservation rr) {
		this.hotelName = rr.getRoom().getHotel().getHotelName();
		this.roomNumber = rr.getRoom().getRoomNumber();
		this.reservationEnd = rr.getReservationEnd();
		this.reservationStart = rr.getReservationStart();
		this.price = rr.getPrice();
		this.hotelId = rr.getRoom().getHotel().getHotelID();
		this.roomId = rr.getRoom().getRoomID();
		this.id = rr.getId();
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getReservationStart() {
		return reservationStart;
	}

	public void setReservationStart(Date reservationStart) {
		this.reservationStart = reservationStart;
	}

	public Date getReservationEnd() {
		return reservationEnd;
	}

	public void setReservationEnd(Date reservationEnd) {
		this.reservationEnd = reservationEnd;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
