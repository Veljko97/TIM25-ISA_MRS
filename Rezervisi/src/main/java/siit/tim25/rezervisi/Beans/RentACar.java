package siit.tim25.rezervisi.Beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import siit.tim25.rezervisi.Beans.Grades.RentACarGrade;
import siit.tim25.rezervisi.Beans.users.RentACarAdmin;

@Entity
public class RentACar {
	
	@Id
	@GeneratedValue
	private Integer rentACarID;
	
	@Column(name = "rentACarName" , nullable = false)
	private String rentACarName;
	
	@Column(name = "rentACarAddress")
	private String rentACarAddress;
	
	@Column(name = "rentACarDescription")
	private String rentACarDescription;
	
	@Column
    private String image;
	
	@OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RentACarOffer> offersPriceList = new HashSet<RentACarOffer>();
	
	@OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<RentACarBranch> rentACarBranches = new HashSet<RentACarBranch>();
	
	@OneToMany(mappedBy = "rentACar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RentACarGrade> grades = new HashSet<RentACarGrade>();
	
	@Column(name = "rentACarEarning")
	private Double rentACarEarning;
	
	@OneToMany(mappedBy = "rentACar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<RentACarAdmin> admins = new HashSet<RentACarAdmin>();
	
	public RentACar() {
		super();
		this.rentACarEarning = 0.0;
	}
	public RentACar(Integer rentACarID, String rentACarName, String rentACarAddress, String rentACarDescription,
			Set<RentACarOffer> offersPriceList, Set<RentACarBranch> rentACarBranches,
			Double rentACarAverageGrade, Double rentACarEarning) {
		super();
		this.rentACarID = rentACarID;
		this.rentACarName = rentACarName;
		this.rentACarAddress = rentACarAddress;
		this.rentACarDescription = rentACarDescription;
		this.offersPriceList = offersPriceList;
		this.rentACarBranches = rentACarBranches;
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
	public Set<RentACarOffer> getOffersPriceList() {
		return offersPriceList;
	}
	public void setOffersPriceList(Set<RentACarOffer> offersPriceList) {
		this.offersPriceList = offersPriceList;
	}
	public Set<RentACarBranch> getRentACarBranches() {
		return rentACarBranches;
	}
	public void setRentACarBranches(Set<RentACarBranch> rentACarBranches) {
		this.rentACarBranches = rentACarBranches;
	}
	
	public Set<RentACarGrade> getGrades() {
		return grades;
	}
	
	public void setGrades(Set<RentACarGrade> grades) {
		this.grades = grades;
	}
	
	public Double getRentACarEarning() {
		return rentACarEarning;
	}
	public void setRentACarEarning(Double rentACarEarning) {
		this.rentACarEarning = rentACarEarning;
	}
	
	public Set<RentACarAdmin> getAdmins() {
		return admins;
	}
	public void setAdmins(Set<RentACarAdmin> admins) {
		this.admins = admins;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "RentACar [rentACarID=" + rentACarID + ", rentACarName=" + rentACarName + ", rentACarAddress="
				+ rentACarAddress + ", rentACarDescription=" + rentACarDescription + ", offersPriceList="
				+ offersPriceList + ", rentACarBranches=" + rentACarBranches+", rentACarEarning=" + rentACarEarning + "]";
	}

	
}
