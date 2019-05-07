package siit.tim25.rezervisi.Beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import siit.tim25.rezervisi.Beans.Grades.VehicleGrade;

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
	
	@OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<VehicleGrade> grades = new HashSet<VehicleGrade>();

	@OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<VehicleReservation> reservation = new HashSet<VehicleReservation>();
	
	@Column
    private String image;

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
	
	public Set<VehicleGrade> getGrades() {
		return grades;
	}

	public void setGrades(Set<VehicleGrade> grades) {
		this.grades = grades;
	}
	
	public Set<VehicleReservation> getReservation() {
		return reservation;
	}

	public void setReservation(Set<VehicleReservation> reservation) {
		this.reservation = reservation;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
