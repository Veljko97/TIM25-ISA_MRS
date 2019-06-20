package siit.tim25.rezervisi.DTO;

import java.text.ParseException;
import java.util.Set;

import siit.tim25.rezervisi.Beans.RentACarBranch;
import siit.tim25.rezervisi.Beans.Vehicle;

public class VehicleDTO {
	
	private String vehicleName;
	private Integer idVehicle;
	private String branchName;
	private Double averageGrade;
	private Double price;
	
	public VehicleDTO() {
		super();
	}

	public VehicleDTO(String vehicleName, Integer idVehicle, String branchName, Double averageGrade, Double price) {
		super();
		this.vehicleName = vehicleName;
		this.idVehicle = idVehicle;
		this.branchName = branchName;
		this.averageGrade = averageGrade;
		this.price = price;
	}
	
	public VehicleDTO(Vehicle veh) {
		this.vehicleName = veh.getVehicleName();
		this.idVehicle = veh.getId();
		this.branchName = veh.getBranch().getBranchName();
		this.averageGrade = veh.getAverageGrade();
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public Integer getIdVehicle() {
		return idVehicle;
	}

	public void setIdVehicle(Integer idVehicle) {
		this.idVehicle = idVehicle;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public Double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(Double averageGrade) {
		this.averageGrade = averageGrade;
	}
	
	
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Vehicle convert(Set<RentACarBranch> branches) throws ParseException {
		Vehicle v = new Vehicle();
		for(RentACarBranch b: branches) {
			if (b.getBranchName().equals(this.getBranchName())) {
				v.setBranch(b);
			}
		}
		
		v.setVehicleName(this.vehicleName);
		v.setIdVehicle(idVehicle);
		v.setPrice(price);
		
		return v;
	}
  
	@Override
	public String toString() {
		return "VehicleDTO [vehicleName=" + vehicleName + ", idVehicle=" + idVehicle + ", branchName=" + branchName
				+ ", averageGrade=" + averageGrade + "]";
	}
}
