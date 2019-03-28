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



@Entity
public class RentACarBranch {
	
	@Id
	@GeneratedValue
	private Integer idBranch;
	
	@Column(name = "branchName", nullable = false)
	private String branchName;
	
	@Column(name = "branchAddress")
	private String branchAddress;
	
	@OneToMany(mappedBy = "branch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Vehicle> vehiclesList = new HashSet<Vehicle>();
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private RentACar service;
	
	
	public RentACarBranch() {
		super();
	}
	public RentACarBranch(String branchName, String branchAddress) {
		super();
		this.branchName = branchName;
		this.branchAddress = branchAddress;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	public Integer getIdBranch() {
		return idBranch;
	}
	public void setIdBranch(Integer idBranch) {
		this.idBranch = idBranch;
	}
	public Set<Vehicle> getVehiclesList() {
		return vehiclesList;
	}
	public void setVehiclesList(Set<Vehicle> vehiclesList) {
		this.vehiclesList = vehiclesList;
	}
	public RentACar getService() {
		return service;
	}
	public void setService(RentACar service) {
		this.service = service;
	}
	@Override
	public String toString() {
		return "RentACarBranch [branchName=" + branchName + ", branchAddress=" + branchAddress + "]";
	}
	
	
	
}
