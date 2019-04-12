package siit.tim25.rezervisi.Services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.Beans.Flight;
import siit.tim25.rezervisi.DTO.FlightDTO;
import siit.tim25.rezervisi.Repository.AirLineRepository;
import siit.tim25.rezervisi.Repository.FlightRepository;

@Component
public class FlightServices {
	@Autowired
	private AirLineRepository airLineRepository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	public Flight save(Integer airLineId, Flight f) {
		AirLine a = airLineRepository.findOne(airLineId);
		for(Flight fl: a.getAirLineFlights()) {
			if (fl.getIdFlight() == f.getIdFlight()) {
				return null;
			}
		}
		f.setAirLine(a);
		a.getAirLineFlights().add(f);
		airLineRepository.save(a);
		return f;
	}
	
	public Set<Flight> findAll(Integer airLineId){
		AirLine a = airLineRepository.findOne(airLineId);
		return a.getAirLineFlights();
	}
	
	public Flight findOne(Integer airLineId, Integer flightId)
	{
		AirLine a = airLineRepository.findOne(airLineId);
		
		Flight fl = null;
		for(Flight f: a.getAirLineFlights()) {
			if (f.getIdFlight() == flightId) {
				fl = f;
			}
		}
		return fl;
	}
	
	public FlightDTO findOneAndConvert(Integer airLineId, Integer flightId)
	{
		Flight fl = this.findOne(airLineId, flightId);
		return fl.convert();
	}
	
	public Set<FlightDTO> findAllAndConvert(Integer airLineId){
		Set<FlightDTO> listConvertedFlights = new HashSet<FlightDTO>();
		AirLine a = airLineRepository.findOne(airLineId);
		Set<Flight> listFlights = this.findAll(airLineId);
		for(Flight f: listFlights) {
			listConvertedFlights.add(f.convert());
		}
		return listConvertedFlights;
	}
	
	public Flight update(Integer airlineId, Flight f) {
		AirLine a = airLineRepository.findOne(airlineId);
		Flight fl = null;
		for(Flight flight: a.getAirLineFlights()) {
			if (flight.getIdFlight() == f.getIdFlight()) {
				flight = new Flight(f.getStartDestination(), f.getFinalDestination(), f.getTakeOffDate(), f.getLandingDate(), f.getFlightLength(), f.getNumberOfStops(), f.getNumberOfSeats(), f.getTicketPrice(), f.getStopLocations(), f.getFlightTicket(), f.getFlightAverageGrade());
				fl = flight;
			}
		}
		airLineRepository.save(a);
		return fl;
	}
	
	public void delete(Integer airlineId, Integer flightId) {
		AirLine a = airLineRepository.findOne(airlineId);
		Flight f = null;
		for(Flight fl: a.getAirLineFlights()) {
			if (fl.getIdFlight() == flightId) {
				f = fl;
			}
		}
		f.setAirLine(null);
		a.getAirLineFlights().remove(f);
		airLineRepository.save(a);
		flightRepository.delete(f);
	}
}
