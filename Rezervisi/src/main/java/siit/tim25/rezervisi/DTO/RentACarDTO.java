package siit.tim25.rezervisi.DTO;

import java.text.ParseException;
import java.util.List;

import siit.tim25.rezervisi.Beans.Destination;
import siit.tim25.rezervisi.Beans.RentACar;

public class RentACarDTO {
	private Integer rentACarID;
	
	private String rentACarName;
	
	private String rentACarAddress;
	
	private String rentACarDescription;
	
	private String destination;
	private Double averageGrade;
	private Double rentACarEarning;
	private String image;
	
	public RentACarDTO() {
		
	}

	public RentACarDTO(Integer rentACarID, String rentACarName, String rentACarAddress, String rentACarDescription,
			String destination) {
		super();
		this.rentACarID = rentACarID;
		this.rentACarName = rentACarName;
		this.rentACarAddress = rentACarAddress;
		this.rentACarDescription = rentACarDescription;
		this.destination = destination;
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

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public Double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(Double averageGrade) {
		this.averageGrade = averageGrade;
	}


	public Double getRentACarEarning() {
		return rentACarEarning;
	}

	public void setRentACarEarning(Double rentACarEarning) {
		this.rentACarEarning = rentACarEarning;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public RentACar convert(List<Destination> destinations) throws ParseException {
		RentACar r = new RentACar();
		
		for(Destination d: destinations) {
			if (d.getDestinationName().equals(this.getDestination())) {
				r.setDestination(d);
			}
		}

		r.setImage(this.image);
		r.setRentACarName(this.rentACarName);
		r.setRentACarAddress(this.rentACarAddress);
		r.setRentACarDescription(this.rentACarDescription);
		r.setRentACarEarning(this.rentACarEarning);

		return r;
	}
}
