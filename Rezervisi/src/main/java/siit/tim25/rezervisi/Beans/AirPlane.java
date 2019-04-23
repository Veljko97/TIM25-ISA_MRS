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

	@Column(name = "numberOfSeats")
	private Integer numberOfSeats;
	
	public AirPlane() {
		super();
	}

	public AirPlane(Integer id, String name, Integer numberOfSeats) {
		super();
		this.id = id;
		this.name = name;
		this.numberOfSeats = numberOfSeats;
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

	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	
	

}
