package siit.tim25.rezervisi.DTO;


import java.text.ParseException;
import java.util.List;

import siit.tim25.rezervisi.Beans.Destination;
import siit.tim25.rezervisi.Beans.Hotel;

public class HotelDTO {
	private String hotelID;
	private String hotelName;
	private String hotelAddress;
	private String hotelDescription;
	private String destination;
	private String hotelGrade;
	private String hotelEarning;
	private String image;
	
	public HotelDTO() {
		super();
	}
	
	public HotelDTO(String hotelID, String hotelName, String hotelAddress, String hotelDescription, String destination,
			String hotelGrade, String hotelEarning, String image) {
		super();
		this.hotelID = hotelID;
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
		this.hotelDescription = hotelDescription;
		this.destination = destination;
		this.hotelGrade = hotelGrade;
		this.hotelEarning = hotelEarning;
		this.image = image;
	}

	


	@Override
	public String toString() {
		return "HotelDTO [hotelName=" + hotelName + ", hotelAddress=" + hotelAddress + ", hotelDescription="
				+ hotelDescription + ", destination=" + destination + ", hotelGrade=" + hotelGrade + ", hotelEarning="
				+ hotelEarning + "]";
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



	public String getHotelID() {
		return hotelID;
	}

	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}

	public String getDestination() {
		return destination;
	}



	public void setDestination(String destination) {
		this.destination = destination;
	}



	public String getHotelGrade() {
		return hotelGrade;
	}



	public void setHotelGrade(String hotelGrade) {
		this.hotelGrade = hotelGrade;
	}



	public String getHotelEarning() {
		return hotelEarning;
	}



	public void setHotelEarning(String hotelEarning) {
		this.hotelEarning = hotelEarning;
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
		h.setHotelEarning(this.hotelEarning != null ? Double.parseDouble(this.hotelEarning) : 0);

		return h;
	}
}
