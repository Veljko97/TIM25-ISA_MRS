package siit.tim25.rezervisi.Repository;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.Flight;

@Component
public class FlightRepository {
	
	ArrayList<Flight> flights = new ArrayList<Flight>();

	public boolean save(Flight f) {
		flights.add(f);
		return true;
	}
	
	public ArrayList<Flight> getFlightList(){
		return flights;
	}
}
