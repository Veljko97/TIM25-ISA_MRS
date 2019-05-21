package siit.tim25.rezervisi.DTO;

import siit.tim25.rezervisi.Beans.RoomReservation;
import siit.tim25.rezervisi.Beans.VehicleReservation;

public class FastReservationDTO {
	private Long start;
	private Long end;
	private Double price;
	private Integer resId;
	private VehicleDTO vehicle;
	private RoomDTO room;
	
	public FastReservationDTO() {}
	
	public FastReservationDTO(RoomReservation roomRes) {
		this.start = roomRes.getReservationStart().getTime();
		this.end = roomRes.getReservationEnd().getTime();
		this.price = roomRes.getPrice();
		this.resId = roomRes.getId();
		this.room = new RoomDTO(roomRes.getRoom());
	}
	
	public FastReservationDTO(VehicleReservation res) {
		this.start = res.getReservationStart().getTime();
		this.end = res.getReservationEnd().getTime();
		this.price = res.getPrice();
		this.resId = res.getId();
		this.vehicle = new VehicleDTO(res.getVehicle());
	}

	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public Long getEnd() {
		return end;
	}
	public void setEnd(Long end) {
		this.end = end;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public VehicleDTO getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleDTO vehicle) {
		this.vehicle = vehicle;
	}

	public RoomDTO getRoom() {
		return room;
	}

	public void setRoom(RoomDTO room) {
		this.room = room;
	}

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}
	
}
