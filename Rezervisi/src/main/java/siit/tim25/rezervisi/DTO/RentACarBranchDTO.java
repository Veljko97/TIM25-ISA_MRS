package siit.tim25.rezervisi.DTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import siit.tim25.rezervisi.Beans.AirPlane;
import siit.tim25.rezervisi.Beans.Destination;
import siit.tim25.rezervisi.Beans.FlightClass;
import siit.tim25.rezervisi.Beans.FlightType;
import siit.tim25.rezervisi.Beans.RentACarBranch;

public class RentACarBranchDTO {
	private String idBranch;
	
	private String branchName;
	
	private String branchAddress;
	
	private String destination;
	
	public RentACarBranchDTO() {
		super();
	}

	public RentACarBranchDTO(String idBranch, String branchName, String branchAddress, String destination) {
		super();
		this.idBranch = idBranch;
		this.branchName = branchName;
		this.branchAddress = branchAddress;
		this.destination = destination;
	}

	public String getIdBranch() {
		return idBranch;
	}

	public void setIdBranch(String idBranch) {
		this.idBranch = idBranch;
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

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
		
	public RentACarBranch convert(Set<Destination> destinations) throws ParseException {
		RentACarBranch r = new RentACarBranch();
		for(Destination d: destinations) {
			if (d.getDestinationName().equals(this.getDestination())) {
				r.setDestination(d);
			}
		}
		
		r.setBranchAddress(this.branchAddress);
		r.setBranchName(branchName);
		return r;
	}
}
