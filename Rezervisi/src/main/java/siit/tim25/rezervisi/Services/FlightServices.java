package siit.tim25.rezervisi.Services;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.Beans.Flight;
import siit.tim25.rezervisi.Beans.FlightClass;
import siit.tim25.rezervisi.Beans.FlightType;
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
	
	public Page<FlightDTO> findAllAndConvert(Integer airLineId, Pageable pageable) {
		Page<Flight> fl = flightRepository.findAllByAirLineId(airLineId, pageable);
		return fl.map(new Converter<Flight, FlightDTO>() {
		    @Override
		    public FlightDTO convert(Flight entity) {
		        return entity.convert();
		    }
		});
	}
	
	public Set<FlightDTO> search(String type, String flightClass, String from, String to, Long takeOff, Long landing, 
			String numberOfPeople, String airLineName, String flightLength, String priceFrom, String priceTo, Pageable pageable) throws ParseException {
		Date date1 = new Date(takeOff);
		Date date2 = new Date(landing);
		Page<Flight> flist = flightRepository.search(FlightType.valueOf(type), FlightClass.valueOf(flightClass), from, to, date1, date2,  priceFrom.equals("") ? null : Double.parseDouble(priceFrom), priceTo.equals("") ? null : Double.parseDouble(priceTo), airLineName, flightLength.equals("") ? null : flightLength, pageable);
		Set<FlightDTO> listConvertedFlights = new HashSet<FlightDTO>();
		for(Flight f: flist) {
			listConvertedFlights.add(f.convert());
		}
		return listConvertedFlights;
	}
	
	public Set<FlightDTO> findAllAndConvert(Integer airLineId){
		Set<FlightDTO> listConvertedFlights = new HashSet<FlightDTO>();
		Set<Flight> listFlights = this.findAll(airLineId);
		for(Flight f: listFlights) {
			listConvertedFlights.add(f.convert());
		}
		return listConvertedFlights;
	}
	
	public Page<FlightDTO> findAllAndConvert(Pageable pageable){
		Page<Flight> listFlights = flightRepository.findAll(pageable);
		return listFlights.map(new Converter<Flight, FlightDTO>() {
		    @Override
		    public FlightDTO convert(Flight entity) {
		        return entity.convert();
		    }
		});
	}
	
	
	public Flight update(Integer airlineId, Flight f) {
		AirLine a = airLineRepository.findOne(airlineId);
		Flight fl = null;
		for(Flight flight: a.getAirLineFlights()) {
			if (flight.getIdFlight() == f.getIdFlight()) {
				flight.setStartDestination(f.getStartDestination());
				flight.setFinalDestination(f.getFinalDestination());
				flight.setTakeOffDate(f.getTakeOffDate());
				flight.setLandingDate(f.getLandingDate());
				flight.setFlightLength(f.getFlightLength());
				flight.setNumberOfStops(f.getNumberOfStops());
				flight.setAirplane(f.getAirplane());
				flight.setTicketPrice(f.getTicketPrice());
				flight.setType(f.getType());
				flight.setFlightClass(f.getFlightClass());
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
