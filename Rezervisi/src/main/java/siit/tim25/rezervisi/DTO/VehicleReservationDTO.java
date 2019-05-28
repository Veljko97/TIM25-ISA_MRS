package siit.tim25.rezervisi.DTO;

import java.util.Date;

import siit.tim25.rezervisi.Beans.VehicleReservation;


public class VehicleReservationDTO {
	private Date reservationStart;
	
	private Date reservationEnd;
	
	private Double price;
	
	private String vehicleName;
	
	private Integer vehicleId;
	
	private Integer rentACarId;
	
	private Integer id;
	
	public VehicleReservationDTO(VehicleReservation vr) {
		this.reservationEnd = vr.getReservationEnd();
		this.reservationStart = vr.getReservationStart();
		this.price = vr.getPrice();
		this.vehicleName = vr.getVehicle().getVehicleName();
		this.rentACarId = vr.getVehicle().getBranch().getService().getRentACarID();
		this.vehicleId = vr.getVehicle().getId();
		this.id = vr.getId();
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

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Integer getRentACarId() {
		return rentACarId;
	}

	public void setRentACarId(Integer rentACarId) {
		this.rentACarId = rentACarId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
}
