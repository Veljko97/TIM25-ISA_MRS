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

import siit.tim25.rezervisi.Beans.users.HotelAdmin;

@Entity
public class Hotel {
	
	@Id
	@GeneratedValue
	private Integer hotelID;
	
	@Column(nullable = false)
	private String hotelName;
	
	@Column(nullable = false)
	private String hotelAddress;
	
	@Column
	private String hotelDescription;
	
	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<HotelOffer> offersPriceList= new HashSet<HotelOffer>();
	
	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Room> roomList = new HashSet<Room>();
	
	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<HotelAdmin> admins = new HashSet<HotelAdmin>();
	
	@Column
	private String roomConfig;
	
	@Column
	private Double hotelAverageGrade;
	
	@Column
	private Double hotelEarning;
	
	public Hotel() {
		super();
		this.hotelAverageGrade = 0.0;
		this.hotelEarning = 0.0;
	}
	
	

	public Hotel(Integer hotelID, String hotelName, String hotelAddress, String hotelDescription,
			Set<HotelOffer> offersPriceList, Set<Room> roomList, String roomConfig, Double hotelAverageGrade,
			Double hotelEarning) {
		super();
		this.hotelID = hotelID;
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
		this.hotelDescription = hotelDescription;
		this.offersPriceList = offersPriceList;
		this.roomList = roomList;
		this.roomConfig = roomConfig;
		this.hotelAverageGrade = 0.0;
		this.hotelEarning = 0.0;
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

	public Set<HotelOffer> getOffersPriceList() {
		return offersPriceList;
	}

	public void setOffersPriceList(Set<HotelOffer> offersPriceList) {
		this.offersPriceList = offersPriceList;
	}

	public Set<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(Set<Room> roomList) {
		this.roomList = roomList;
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

	public Set<HotelAdmin> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<HotelAdmin> admins) {
		this.admins = admins;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
	    if (obj == this) return true;
	    if (!(obj instanceof Hotel)) return false;
	    Hotel o = (Hotel) obj;
	    
		return o.hotelID == this.hotelID;
	}
	
}
	
	

