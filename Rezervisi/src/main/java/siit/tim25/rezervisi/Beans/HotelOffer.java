package siit.tim25.rezervisi.Beans;

public class HotelOffer {
	
	String offerName;
	String offerDescription;
	Double offerPrice;
	
	
	
	public HotelOffer() {
		super();
	}
	public HotelOffer(String offerName, String offerDescription, Double offerPrice) {
		super();
		this.offerName = offerName;
		this.offerDescription = offerDescription;
		this.offerPrice = offerPrice;
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
