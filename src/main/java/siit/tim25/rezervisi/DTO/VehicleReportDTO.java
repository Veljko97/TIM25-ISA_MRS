package siit.tim25.rezervisi.DTO;

import java.util.Date;

import siit.tim25.rezervisi.Beans.TicketStatus;
import siit.tim25.rezervisi.Beans.VehicleReservation;

public class VehicleReportDTO {
	private Integer Id;
	
	private Integer vehicleId;
		
	private Date reservationStart;
	
	private Date reservationEnd;
		
	private TicketStatus status;
	
	private Long created;
	
	private Double totalPrice;
	
	public VehicleReportDTO(VehicleReservation v, Double totalPrice, Integer created) {
		this.Id = v.getId();
		this.vehicleId = v.getVehicle().getIdVehicle();
		this.reservationStart = v.getReservationStart();
		this.reservationEnd = v.getReservationEnd();		
		this.totalPrice = totalPrice;
		this.status = v.getStatus();
		this.created = new Long(created);
	}
	
	public VehicleReportDTO(VehicleReservation v, Double totalPrice, Date created) {
		this.Id = v.getId();
		this.vehicleId = v.getVehicle().getIdVehicle();
		this.reservationStart = v.getReservationStart();
		this.reservationEnd = v.getReservationEnd();		
		this.totalPrice = totalPrice;
		this.status = v.getStatus();
		this.created = created.getTime();
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
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
