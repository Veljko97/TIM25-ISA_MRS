package siit.tim25.rezervisi.Beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RentACarOffer {
	
	@Id
	@GeneratedValue
	private Integer idOffer;
	
	@Column(nullable = false)
	private String offerName;
	
	@Column
	private String offerDescription;
	
	@Column
	private Double offerPrice;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private RentACar service;
	
	public RentACarOffer(String offerName, String offerDescription, Double offerPrice) {
		super();
		this.offerName = offerName;
		this.offerDescription = offerDescription;
		this.offerPrice = offerPrice;
	}


	public RentACarOffer() {
		super();
	}
	
	public Integer getIdOffer() {
		return idOffer;
	}


	public void setIdOffer(Integer idOffer) {
		this.idOffer = idOffer;
	}


	public RentACar getService() {
		return service;
	}


	public void setService(RentACar service) {
		this.service = service;
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
		return "RentACarOffer [offerName=" + offerName + ", offerDescription=" + offerDescription + ", offerPrice="
				+ offerPrice + "]";
	}
	
	
	
	
	
}
