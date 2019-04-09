package siit.tim25.rezervisi.Beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle {
	
	@Id
	@GeneratedValue
	private Integer idVehicle;

	@Column(name = "vehicleName", nullable = false)
	private String vehicleName;
	
	@Column(name = "vehicleGrade", nullable = false)
	private String vehicleGrade;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private RentACarBranch branch;

	public Vehicle() {
		this.vehicleName = "";
		this.vehicleGrade = "";
	}
	
	public Vehicle(Integer id, String vehicleName, String vehicleGrade) {
		super();
		this.idVehicle = id;
		this.vehicleName = vehicleName;
		this.vehicleGrade = vehicleGrade;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getVehicleGrade() {
		return vehicleGrade;
	}

	public void setVehicleGrade(String vehicleGrade) {
		this.vehicleGrade = vehicleGrade;
	}

	public Integer getId() {
		return idVehicle;
	}

	public void setId(Integer id) {
		this.idVehicle = id;
	}
	

	public Integer getIdVehicle() {
		return idVehicle;
	}

	public void setIdVehicle(Integer idVehicle) {
		this.idVehicle = idVehicle;
	}

	public RentACarBranch getBranch() {
		return branch;
	}

	public void setBranch(RentACarBranch branch) {
		this.branch = branch;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
	    if (obj == this) return true;
	    if (!(obj instanceof Vehicle)) return false;
	    Vehicle o = (Vehicle) obj;
	    
		return o.idVehicle == this.idVehicle;
	}
	
}
