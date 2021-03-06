package siit.tim25.rezervisi.Beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Destination {
	
	@Id
	@GeneratedValue
	private Integer idDestination;
	
	@Column(name = "destinationName", nullable = false)
	private String destinationName;
	
	@Column(name = "destinationDescription")
	private String destinationDescription;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private AirLine airLine; 
	
	@Column
    private String image;

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
	
	
	
	public AirLine getAirLine() {
		return airLine;
	}

	public void setAirLine(AirLine airLine) {
		this.airLine = airLine;
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
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Destination [destinationName=" + destinationName + ", destinationDescription=" + destinationDescription
				+ "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
	    if (obj == this) return true;
	    if (!(obj instanceof Destination)) return false;
	    Destination o = (Destination) obj;
	    
		return o.idDestination == this.idDestination;
	}
	
	
	
	
}
