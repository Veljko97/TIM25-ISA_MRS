package siit.tim25.rezervisi.Beans;

import java.util.ArrayList;

public class RentACar {
	
	Integer rentACarID;
	String rentACarName;
	String rentACarAddress;
	String rentACarDescription;
	ArrayList<RentACarOffer> offersPriceList;
	ArrayList<Vehicle> vehiclesList;
	ArrayList<RentACarBranch> rentACarBranches;
	Double rentACarAverageGrade;
	Double rentACarEarning;
	
	public RentACar() {
		super();
	}
	public RentACar(Integer rentACarID, String rentACarName, String rentACarAddress, String rentACarDescription,
			ArrayList<RentACarOffer> offersPriceList, ArrayList<Vehicle> vehiclesList,
			ArrayList<RentACarBranch> rentACarBranches, Double rentACarAverageGrade, Double rentACarEarning) {
		super();
		this.rentACarID = rentACarID;
		this.rentACarName = rentACarName;
		this.rentACarAddress = rentACarAddress;
		this.rentACarDescription = rentACarDescription;
		this.offersPriceList = offersPriceList;
		this.vehiclesList = vehiclesList;
		this.rentACarBranches = rentACarBranches;
		this.rentACarAverageGrade = rentACarAverageGrade;
		this.rentACarEarning = rentACarEarning;
	}
	public Integer getRentACarID() {
		return rentACarID;
	}
	public void setRentACarID(Integer rentACarID) {
		this.rentACarID = rentACarID;
	}
	public String getRentACarName() {
		return rentACarName;
	}
	public void setRentACarName(String rentACarName) {
		this.rentACarName = rentACarName;
	}
	public String getRentACarAddress() {
		return rentACarAddress;
	}
	public void setRentACarAddress(String rentACarAddress) {
		this.rentACarAddress = rentACarAddress;
	}
	public String getRentACarDescription() {
		return rentACarDescription;
	}
	public void setRentACarDescription(String rentACarDescription) {
		this.rentACarDescription = rentACarDescription;
	}
	public ArrayList<RentACarOffer> getOffersPriceList() {
		return offersPriceList;
	}
	public void setOffersPriceList(ArrayList<RentACarOffer> offersPriceList) {
		this.offersPriceList = offersPriceList;
	}
	public ArrayList<Vehicle> getVehiclesList() {
		return vehiclesList;
	}
	public void setVehiclesList(ArrayList<Vehicle> vehiclesList) {
		this.vehiclesList = vehiclesList;
	}
	public ArrayList<RentACarBranch> getRentACarBranches() {
		return rentACarBranches;
	}
	public void setRentACarBranches(ArrayList<RentACarBranch> rentACarBranches) {
		this.rentACarBranches = rentACarBranches;
	}
	public Double getRentACarAverageGrade() {
		return rentACarAverageGrade;
	}
	public void setRentACarAverageGrade(Double rentACarAverageGrade) {
		this.rentACarAverageGrade = rentACarAverageGrade;
	}
	public Double getRentACarEarning() {
		return rentACarEarning;
	}
	public void setRentACarEarning(Double rentACarEarning) {
		this.rentACarEarning = rentACarEarning;
	}
	@Override
	public String toString() {
		return "RentACar [rentACarID=" + rentACarID + ", rentACarName=" + rentACarName + ", rentACarAddress="
				+ rentACarAddress + ", rentACarDescription=" + rentACarDescription + ", offersPriceList="
				+ offersPriceList + ", vehiclesList=" + vehiclesList + ", rentACarBranches=" + rentACarBranches
				+ ", rentACarAverageGrade=" + rentACarAverageGrade + ", rentACarEarning=" + rentACarEarning + "]";
	}

	
}
