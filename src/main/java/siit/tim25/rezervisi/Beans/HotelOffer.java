package siit.tim25.rezervisi.Beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class HotelOffer {
	@Id
	@GeneratedValue
	private Integer idOffer;
	
	@Column(nullable = false)
	private String offerName;
	
	@Column
	private String offerDescription;
	
	@Column
	private Double offerPrice;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Hotel hotel;
	
	
	public HotelOffer() {
		super();
	}
	
	public HotelOffer(Integer idOffer, String offerName, String offerDescription, Double offerPrice, Hotel hotel) {
		super();
		this.idOffer = idOffer;
		this.offerName = offerName;
		this.offerDescription = offerDescription;
		this.offerPrice = offerPrice;
		this.hotel = hotel;
	}
	
	public Integer getIdOffer() {
		return idOffer;
	}

	public void setIdOffer(Integer idOffer) {
		this.idOffer = idOffer;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	public String getOfferDescription() {
		return offerDescription;
	}
	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}
	public Double getOfferPrice() {
		return offerPrice;
	}
	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
	}
	@Override
	public String toString() {
		return "HotelOffer [offerName=" + offerName + ", offerDescription=" + offerDescription + ", offerPrice="
				+ offerPrice + "]";
	}
	
	
	
	
}
