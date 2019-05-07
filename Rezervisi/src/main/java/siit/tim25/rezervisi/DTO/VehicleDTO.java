package siit.tim25.rezervisi.DTO;

import java.text.ParseException;
import java.util.Set;

import siit.tim25.rezervisi.Beans.RentACarBranch;
import siit.tim25.rezervisi.Beans.Vehicle;

public class VehicleDTO {
	
	private String vehicleName;
	private Integer idVehicle;
	private String branchName;
	
	public VehicleDTO() {
		super();
	}

	public VehicleDTO(String vehicleName, Integer idVehicle, String branchName) {
		super();
		this.vehicleName = vehicleName;
		this.idVehicle = idVehicle;
		this.branchName = branchName;
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
	
	public Vehicle convert(Set<RentACarBranch> branches) throws ParseException {
		Vehicle v = new Vehicle();
		for(RentACarBranch b: branches) {
			if (b.getBranchName().equals(this.getBranchName())) {
				v.setBranch(b);
			}
		}
		
		v.setVehicleName(this.vehicleName);
		v.setIdVehicle(idVehicle);
		
		return v;
	}

}