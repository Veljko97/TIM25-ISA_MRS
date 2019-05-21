package siit.tim25.rezervisi.Beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import siit.tim25.rezervisi.Beans.Grades.HotelGrade;
import siit.tim25.rezervisi.Beans.users.HotelAdmin;
import siit.tim25.rezervisi.DTO.HotelDTO;

@Entity
public class Hotel {
	
	@Id
	@GeneratedValue
	private Integer hotelID;
	
	@Column(nullable = false)
	private String hotelName;
	
	@Column(nullable = false)
	private String hotelAddress;
	
	@ManyToOne
	private Destination destination;
	
	@Column
	private String hotelDescription;
	
	@Column
    private String image;
	
	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<HotelOffer> offersPriceList= new HashSet<HotelOffer>();
	
	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Room> roomList = new HashSet<Room>();
	
	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<HotelAdmin> admins = new HashSet<HotelAdmin>();
	
	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<HotelGrade> grades = new HashSet<HotelGrade>();
	
	@Column
	private String roomConfig;
	
	@Column
	private Double hotelEarning;
	
	@Column
	private Double averageGrade;
	
	public Hotel() {
		super();
		this.hotelEarning = 0.0;
	}

	public Hotel(Integer hotelID, String hotelName, String hotelAddress, Destination destination,
			String hotelDescription, Set<HotelOffer> offersPriceList, Set<Room> roomList, Set<HotelAdmin> admins,
			Set<HotelGrade> grades, String roomConfig, Double hotelEarning) {
		super();
		this.hotelID = hotelID;
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
		this.destination = destination;
		this.hotelDescription = hotelDescription;
		this.offersPriceList = offersPriceList;
		this.roomList = roomList;
		this.admins = admins;
		this.grades = grades;
		this.roomConfig = roomConfig;
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

	
	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public Set<HotelGrade> getGrades() {
		return grades;
	}

	public void setGrades(Set<HotelGrade> grades) {
		this.grades = grades;
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
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(Double averageGrade) {
		this.averageGrade = averageGrade;
	}

	
	@Override
	public String toString() {
		return "Hotel [hotelID=" + hotelID + ", hotelName=" + hotelName + ", hotelAddress=" + hotelAddress
				+ ", destination=" + destination + ", hotelDescription=" + hotelDescription + ", offersPriceList="
				+ offersPriceList + ", roomList=" + roomList + ", admins=" + admins + ", grades=" + grades
				+ ", roomConfig=" + roomConfig + ", hotelEarning=" + hotelEarning + "]";
	}



	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
	    if (obj == this) return true;
	    if (!(obj instanceof Hotel)) return false;
	    Hotel o = (Hotel) obj;
	    
		return o.hotelID == this.hotelID;
	}
	
	public HotelDTO convert() {
		return new HotelDTO(this.hotelID, this.hotelName, this.hotelAddress, this.hotelDescription, this.destination.getDestinationName(), this.getAverageGrade(), this.hotelEarning, this.image);

	}
	
}
	
	

