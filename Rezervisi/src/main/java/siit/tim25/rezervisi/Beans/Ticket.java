package siit.tim25.rezervisi.Beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ticket {
	@Id
	@GeneratedValue
	private Integer idTicket;
	
	@Column
	private Double ticketPrice;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Flight flight;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private AirLine airLine;
	
	public Ticket() {
		super();
	}
	
	public Ticket(Double ticketPrice, Flight flight) {
		super();
		this.ticketPrice = ticketPrice;
		this.flight = flight;
	}
	
	public AirLine getAirLine() {
		return airLine;
	}

	public void setAirLine(AirLine airLine) {
		this.airLine = airLine;
	}

	public Double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	public Integer getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(Integer idTicket) {
		this.idTicket = idTicket;
	}

	@Override
	public String toString() {
		return "Ticket [ticketPrice=" + ticketPrice + ", flight=" + flight + "]";
	}
	
	
	
	
}
