package siit.tim25.rezervisi.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.Flight;
import siit.tim25.rezervisi.Repository.FlightRepository;

@Component
public class FlightServices {
	@Autowired
	private FlightRepository flightRepository;
	
	public Flight save(Flight flight) {
		return flightRepository.save(flight);
	}
	
	public List<Flight> findAll(){
		return flightRepository.findAll();
	}
}
