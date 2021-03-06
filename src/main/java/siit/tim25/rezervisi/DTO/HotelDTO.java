package siit.tim25.rezervisi.DTO;


import java.text.ParseException;
import java.util.List;

import siit.tim25.rezervisi.Beans.Destination;
import siit.tim25.rezervisi.Beans.Hotel;

public class HotelDTO {
	private Integer hotelID;
	private String hotelName;
	private String hotelAddress;
	private String hotelDescription;
	private String destination;
	private Double averageGrade;
	private String image;
	
	public HotelDTO() {
		super();
	}
	

	public HotelDTO(Integer hotelID, String hotelName, String hotelAddress, String hotelDescription, String destination,
			Double averageGrade, String image) {
		super();
		this.hotelID = hotelID;
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
		this.hotelDescription = hotelDescription;
		this.destination = destination;
		this.averageGrade = averageGrade;
		this.image = image;
	}

	public HotelDTO(Hotel hotel) {
		this.hotelID = hotel.getHotelID();
		this.hotelName = hotel.getHotelName();
		this.hotelAddress = hotel.getHotelAddress();
		this.hotelDescription = hotel.getHotelDescription();
		this.destination = hotel.getDestination().getDestinationName();
		this.averageGrade = hotel.getAverageGrade();
		this.image = hotel.getImage();
	}


	@Override
	public String toString() {
		return "HotelDTO [hotelName=" + hotelName + ", hotelAddress=" + hotelAddress + ", hotelDescription="
				+ hotelDescription + ", destination=" + destination + ", averageGrade=" + averageGrade + "]";
	}



	public String getHotelName() {
		return hotelName;
	}



	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}



	public String getHotelAddress() {
		return hotelAddress;
	}



	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}



	public String getHotelDescription() {
		return hotelDescription;
	}



	public void setHotelDescription(String hotelDescription) {
		this.hotelDescription = hotelDescription;
	}



	public Integer getHotelID() {
		return hotelID;
	}

	public void setHotelID(Integer hotelID) {
		this.hotelID = hotelID;
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



	public void setAverageGrade(Double hotelGrade) {
		this.averageGrade = hotelGrade;
	}



	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Hotel convert(List<Destination> destinations) throws ParseException {
		Hotel h = new Hotel();
		
		for(Destination d: destinations) {
			if (d.getDestinationName().equals(this.getDestination())) {
				h.setDestination(d);
			}
		}

		h.setImage(this.image);
		h.setHotelName(this.hotelName);
		h.setHotelAddress(this.hotelAddress);
		h.setHotelDescription(this.hotelDescription);

		return h;
	}
}
