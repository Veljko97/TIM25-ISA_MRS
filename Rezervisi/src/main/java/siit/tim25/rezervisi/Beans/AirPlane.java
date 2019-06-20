package siit.tim25.rezervisi.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class AirPlane {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "numberOfEconomyClassSeats")
	private Integer numberOfEconomyClassSeats;
	
	@Column(name = "numberOfFirstClassSeats")
	private Integer numberOfFirstClassSeats;
	
	@Column(name = "numberOfBusinessClassSeats")
	private Integer numberOfBusinessClassSeats;
	
	@Column(name = "luggage")
	private Double luggage;
	
	
	public AirPlane() {
		super();
	}


	public AirPlane(Integer id, String name, Integer numberOfEconomyClassSeats, Integer numberOfFirstClassSeats,
			Integer numberOfBusinessClassSeats, Double luggage) {
		super();
		this.id = id;
		this.name = name;
		this.numberOfEconomyClassSeats = numberOfEconomyClassSeats;
		this.numberOfFirstClassSeats = numberOfFirstClassSeats;
		this.numberOfBusinessClassSeats = numberOfBusinessClassSeats;
		this.luggage = luggage;
	}
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getNumberOfEconomyClassSeats() {
		return numberOfEconomyClassSeats;
	}


	public void setNumberOfEconomyClassSeats(Integer numberOfEconomyClassSeats) {
		this.numberOfEconomyClassSeats = numberOfEconomyClassSeats;
	}


	public Integer getNumberOfFirstClassSeats() {
		return numberOfFirstClassSeats;
	}


	public void setNumberOfFirstClassSeats(Integer numberOfFirstClassSeats) {
		this.numberOfFirstClassSeats = numberOfFirstClassSeats;
	}


	public Integer getNumberOfBusinessClassSeats() {
		return numberOfBusinessClassSeats;
	}


	public void setNumberOfBusinessClassSeats(Integer numberOfBusinessClassSeats) {
		this.numberOfBusinessClassSeats = numberOfBusinessClassSeats;
	}


	public Double getLuggage() {
		return luggage;
	}


	public void setLuggage(Double luggage) {
		this.luggage = luggage;
	}
	
	
	

}
