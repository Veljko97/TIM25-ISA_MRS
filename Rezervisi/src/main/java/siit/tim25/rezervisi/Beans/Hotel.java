package siit.tim25.rezervisi.Beans;

import java.util.ArrayList;


public class Hotel {
	
	Integer hotelID;
	String hotelName;
	String hotelAddress;
	String hotelDescription;
	ArrayList <HotelOffer> offersPriceList;
	String roomConfig;
	Double hotelAverageGrade;
	Double hotelEarning;
	
	public Hotel() {
		super();
	}
	
	public Hotel(Integer hotelID, String hotelName, String hotelAddress, String hotelDescription,
			ArrayList<HotelOffer> offersPriceList, String roomConfig, Double hotelAverageGrade, Double hotelEarning) {
		super();
		this.hotelID = hotelID;
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
		this.hotelDescription = hotelDescription;
		this.offersPriceList = offersPriceList;
		this.roomConfig = roomConfig;
		this.hotelAverageGrade = hotelAverageGrade;
		this.hotelEarning = hotelEarning;
	}
	public Integer getHotelID() {
		return hotelID;
	}
	public void setHotelID(Integer hotelID) {
		this.hotelID = hotelID;
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
	public ArrayList<HotelOffer> getOffersPriceList() {
		return offersPriceList;
	}
	public void setOffersPriceList(ArrayList<HotelOffer> offersPriceList) {
		this.offersPriceList = offersPriceList;
	}
	public String getRoomConfig() {
		return roomConfig;
	}
	public void setRoomConfig(String roomConfig) {
		this.roomConfig = roomConfig;
	}
	public Double getHotelAverageGrade() {
		return hotelAverageGrade;
	}
	public void setHotelAverageGrade(Double hotelAverageGrade) {
		this.hotelAverageGrade = hotelAverageGrade;
	}
	public Double getHotelEarning() {
		return hotelEarning;
	}
	public void setHotelEarning(Double hotelEarning) {
		this.hotelEarning = hotelEarning;
	}

	@Override
	public String toString() {
		return "Hotel [hotelID=" + hotelID + ", hotelName=" + hotelName + ", hotelAddress=" + hotelAddress
				+ ", hotelDescription=" + hotelDescription + ", offersPriceList=" + offersPriceList + ", roomConfig="
				+ roomConfig + ", hotelAverageGrade=" + hotelAverageGrade + ", hotelEarning=" + hotelEarning + "]";
	}
	
	
	
}
	
	

