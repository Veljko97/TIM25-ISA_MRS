package siit.tim25.rezervisi.Beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Destination {
	
	@Id
	@GeneratedValue
	private Integer idDestination;
	
	@Column(name = "destinationName", nullable = false)
	private String destinationName;
	
	@Column(name = "destinationDescription")
	private String destinationDescription;
	
	@OneToMany(mappedBy = "destination", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AirLineDestination> airLineDestination; 

	public Destination() {
		super();
	}
	
	public Destination (String destinationName) {
		super();
		this.destinationName = destinationName;
	}
	
	public Destination(String destinationName, String destinationDescription) {
		super();
		this.destinationName = destinationName;
		this.destinationDescription = destinationDescription;
	}
	
	public String getDestinationName() {
		return destinationName;
	}
	
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public String getDestinationDescription() {
		return destinationDescription;
	}
	public void setDestinationDescription(String destinationDescription) {
		this.destinationDescription = destinationDescription;
	}
	
	public Integer getIdDestination() {
		return idDestination;
	}

	public void setIdDestination(Integer idDestination) {
		this.idDestination = idDestination;
	}

	@Override
	public String toString() {
		return "Destination [destinationName=" + destinationName + ", destinationDescription=" + destinationDescription
				+ "]";
	}
	
	
	
	
}
