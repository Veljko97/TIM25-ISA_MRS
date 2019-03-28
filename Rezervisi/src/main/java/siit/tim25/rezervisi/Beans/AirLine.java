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
	
	@OneToMany(mappedBy = "airLine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AirLineDestination> airLineDestinations = new HashSet<AirLineDestination>();
	
	@OneToMany(mappedBy = "airLine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Flight> airLineFlights = new HashSet<Flight>();
	
	@OneToMany(mappedBy = "airLine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Ticket> offers = new HashSet<Ticket>();
	
	@Column(name = "airLineAverageGrade", nullable = false)
	private Double airLineAverageGrade;
	
	@Column(name = "airlineEarning", nullable = false)
	private Double airlineEarning;
	
    public AirLine() {
		super();
	}
	
	public AirLine(Integer airLineID, String airLineName, String airLineAddress, String airLineDescription,
			Set<AirLineDestination> airLineDestinations, Set<Flight> airLineFlights, Set<Ticket> offers,
			Double airLineAverageGrade, Double airlineEarning) {
		super();
		this.airLineID = airLineID;
		this.airLineName = airLineName;
		this.airLineAddress = airLineAddress;
		this.airLineDescription = airLineDescription;
		this.airLineDestinations = airLineDestinations;
		this.airLineFlights = airLineFlights;
		this.offers = offers;
		this.airLineAverageGrade = airLineAverageGrade;
		this.airlineEarning = airlineEarning;
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
	public Set<AirLineDestination> getAirLineDestinations() {
		return airLineDestinations;
	}
	public void setAirLineDestinations(Set<AirLineDestination> airLineDestinations) {
		this.airLineDestinations = airLineDestinations;
	}
	public Set<Flight> getAirLineFlights() {
		return airLineFlights;
	}
	public void setAirLineFlights(Set<Flight> airLineFlights) {
		this.airLineFlights = airLineFlights;
	}
	public Set<Ticket> getoffers() {
		return offers;
	}
	public void setoffers(Set<Ticket> offers) {
		this.offers = offers;
	}
	public Double getAirLineAverageGrade() {
		return airLineAverageGrade;
	}
	public void setAirLineAverageGrade(Double airLineAverageGrade) {
		this.airLineAverageGrade = airLineAverageGrade;
	}
	public Double getAirlineEarning() {
		return airlineEarning;
	}
	public void setAirlineEarning(Double airlineEarning) {
		this.airlineEarning = airlineEarning;
	}

	@Override
	public String toString() {
		return "AirLine [airLineID=" + airLineID + ", airLineName=" + airLineName + ", airLineAddress=" + airLineAddress
				+ ", airLineDescription=" + airLineDescription + ", airLineDestinations=" + airLineDestinations
				+ ", airLineFlights=" + airLineFlights + ", offers=" + offers + ", airLineAverageGrade="
				+ airLineAverageGrade + ", airlineEarning=" + airlineEarning + "]";
	}
	
}
