package siit.tim25.rezervisi.Beans;

public class Ticket {
	
	Double ticketPrice;
	Flight flight;
	
	public Ticket() {
		super();
	}
	
	public Ticket(Double ticketPrice, Flight flight) {
		super();
		this.ticketPrice = ticketPrice;
		this.flight = flight;
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

	@Override
	public String toString() {
		return "Ticket [ticketPrice=" + ticketPrice + ", flight=" + flight + "]";
	}
	
	
	
	
}
