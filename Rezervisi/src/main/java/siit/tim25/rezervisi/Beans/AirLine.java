package siit.tim25.rezervisi.Beans;

import java.util.ArrayList;

public class AirLine {
	
	Integer airLineID;
	String airLineName;
	String airLineAddress;
	String airLineDescription;
	ArrayList<Destination> airLineDestinations;
	ArrayList<Flight> airLineFlights;
	ArrayList<Ticket> tickets;
	Double airLineAverageGrade;
	Double airlineEarning;
	
    public AirLine() {
		super();
	}
	
	public AirLine(Integer airLineID, String airLineName, String airLineAddress, String airLineDescription,
			ArrayList<Destination> airLineDestinations, ArrayList<Flight> airLineFlights, ArrayList<Ticket> tickets,
			Double airLineAverageGrade, Double airlineEarning) {
		super();
		this.airLineID = airLineID;
		this.airLineName = airLineName;
		this.airLineAddress = airLineAddress;
		this.airLineDescription = airLineDescription;
		this.airLineDestinations = airLineDestinations;
		this.airLineFlights = airLineFlights;
		this.tickets = tickets;
		this.airLineAverageGrade = airLineAverageGrade;
		this.airlineEarning = airlineEarning;
	}
	public Integer getAirLineID() {
		return airLineID;
	}
	public void setAirLineID(Integer airLineID) {
		this.airLineID = airLineID;
	}
	public String getAirLineName() {
		return airLineName;
	}
	public void setAirLineName(String airLineName) {
		this.airLineName = airLineName;
	}
	public String getAirLineAddress() {
		return airLineAddress;
	}
	public void setAirLineAddress(String airLineAddress) {
		this.airLineAddress = airLineAddress;
	}
	public String getAirLineDescription() {
		return airLineDescription;
	}
	public void setAirLineDescription(String airLineDescription) {
		this.airLineDescription = airLineDescription;
	}
	public ArrayList<Destination> getAirLineDestinations() {
		return airLineDestinations;
	}
	public void setAirLineDestinations(ArrayList<Destination> airLineDestinations) {
		this.airLineDestinations = airLineDestinations;
	}
	public ArrayList<Flight> getAirLineFlights() {
		return airLineFlights;
	}
	public void setAirLineFlights(ArrayList<Flight> airLineFlights) {
		this.airLineFlights = airLineFlights;
	}
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}
	public Double getAirLineAverageGrade() {
		return airLineAverageGrade;
	}
	public void setAirLineAverageGrade(Double airLineAverageGrade) {
		this.airLineAverageGrade = airLineAverageGrade;
	}
	public Double getAirlineEarning() {
		return airlineEarning;
	}
	public void setAirlineEarning(Double airlineEarning) {
		this.airlineEarning = airlineEarning;
	}

	@Override
	public String toString() {
		return "AirLine [airLineID=" + airLineID + ", airLineName=" + airLineName + ", airLineAddress=" + airLineAddress
				+ ", airLineDescription=" + airLineDescription + ", airLineDestinations=" + airLineDestinations
				+ ", airLineFlights=" + airLineFlights + ", tickets=" + tickets + ", airLineAverageGrade="
				+ airLineAverageGrade + ", airlineEarning=" + airlineEarning + "]";
	}
	
}
