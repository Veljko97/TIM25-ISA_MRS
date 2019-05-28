package siit.tim25.rezervisi.DTO;

import java.util.Date;
import siit.tim25.rezervisi.Beans.RoomReservation;
import siit.tim25.rezervisi.Beans.TicketStatus;

public class RoomReportDTO {
	private Integer Id;
	
	private Integer roomId;
		
	private Date reservationStart;
	
	private Date reservationEnd;
		
	private TicketStatus status;
	
	private Long created;
	
	private Double totalPrice;

	public RoomReportDTO(RoomReservation r, Double totalPrice, Integer made) {
		this.Id = r.getId();
		this.roomId = r.getRoom().getRoomID();
		this.reservationStart = r.getReservationStart();
		this.reservationEnd = r.getReservationEnd();		
		this.totalPrice = totalPrice;
		this.status = r.getStatus();
		this.created = new Long(made);
	}
	
	public RoomReportDTO(RoomReservation r, Double totalPrice, Date made) {
		this.Id = r.getId();
		this.roomId = r.getRoom().getRoomID();
		this.reservationStart = r.getReservationStart();
		this.reservationEnd = r.getReservationEnd();		
		this.totalPrice = totalPrice;
		this.status = r.getStatus();
		this.created = made.getTime();
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
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

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
