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

import siit.tim25.rezervisi.Beans.Grades.AirLineGrade;
import siit.tim25.rezervisi.Beans.users.AirLineAdmin;

@Entity
public class AirLine {

	@Id
	@GeneratedValue
	private Integer airLineID;

	@Column(name = "airLineName", nullable = false)
	private String airLineName;

	@Column(name = "airLineAddress")
	private String airLineAddress;

	@Column(name = "airLineDescription")
	private String airLineDescription;
	
	@Column(name = "city")
	private String city;
	
	@Column
    private String image;

	@OneToMany(mappedBy = "airLine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Destination> airLineDestinations = new HashSet<Destination>();

	@OneToMany(mappedBy = "airLine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Flight> airLineFlights = new HashSet<Flight>();

	@OneToMany(mappedBy = "airLine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Ticket> offers = new HashSet<Ticket>();

	@OneToMany(mappedBy = "airLine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<AirLineAdmin> admins = new HashSet<AirLineAdmin>();
	
	@OneToMany(mappedBy = "airLine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<AirLineGrade> grades = new HashSet<AirLineGrade>();

	@Column
	private Double averageGrade;
	
	public AirLine() {
		super();
	}

	public AirLine(Integer airLineID, String airLineName, String airLineAddress, String airLineDescription,
			Set<Destination> airLineDestinations, Set<Flight> airLineFlights, Set<Ticket> offers,
			Double airLineAverageGrade, Double airlineEarning, String city) {
		super();
		this.airLineID = airLineID;
		this.airLineName = airLineName;
		this.airLineAddress = airLineAddress;
		this.airLineDescription = airLineDescription;
		this.airLineDestinations = airLineDestinations;
		this.airLineFlights = airLineFlights;
		this.offers = offers;
		this.city = city;
	}

	public Integer getAirLineID() {
		return airLineID;
	}

	public void setAirLineID(Integer airLineID) {
		this.airLineID = airLineID;
	}

	public String getAirLineName() {
		return airLineName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setAirLineName(String airLineName) {
		this.airLineName = airLineName;
	}

	public String getAirLineAddress() {
		return airLineAddress;
	}

	public void setAirLineAddress(String airLineAddress) {
		this.airLineAddress = airLineAddress;
	}

	public String getAirLineDescription() {
		return airLineDescription;
	}

	public void setAirLineDescription(String airLineDescription) {
		this.airLineDescription = airLineDescription;
	}


	public Set<Destination> getAirLineDestinations() {
		return airLineDestinations;
	}

	public void setAirLineDestinations(Set<Destination> airLineDestinations) {
		this.airLineDestinations = airLineDestinations;
	}

	public Set<Flight> getAirLineFlights() {
		return airLineFlights;
	}

	public void setAirLineFlights(Set<Flight> airLineFlights) {
		this.airLineFlights = airLineFlights;
	}
	
	public Set<Ticket> getOffers() {
		return offers;
	}

	public void setOffers(Set<Ticket> offers) {
		this.offers = offers;
	}

	public Set<AirLineGrade> getGrades() {
		return grades;
	}

	public void setGrades(Set<AirLineGrade> grades) {
		this.grades = grades;
	}
	
	public Set<AirLineAdmin> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<AirLineAdmin> admins) {
		this.admins = admins;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(Double averageGrade) {
		this.averageGrade = averageGrade;
	}

	@Override
	public String toString() {
		return "AirLine [airLineID=" + airLineID + ", airLineName=" + airLineName + ", airLineAddress=" + airLineAddress
				+ ", airLineDescription=" + airLineDescription + ", airLineDestinations=" + airLineDestinations
				+ ", airLineFlights=" + airLineFlights + ", offers=" + offers + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
	    if (obj == this) return true;
	    if (!(obj instanceof AirLine)) return false;
	    AirLine o = (AirLine) obj;
	    
		return o.airLineID == this.airLineID;
	}
	
	

}
