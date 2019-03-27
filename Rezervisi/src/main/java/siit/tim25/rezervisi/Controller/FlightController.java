package siit.tim25.rezervisi.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.Beans.Destination;
import siit.tim25.rezervisi.Beans.Flight;
import siit.tim25.rezervisi.Beans.NewFlight;
import siit.tim25.rezervisi.Beans.Ticket;
import siit.tim25.rezervisi.Services.FlightServices;

@RestController
@RequestMapping(path="app/flight")
public class FlightController {
	@Autowired
	private FlightServices flightServices;
	
	@PostMapping(path="/addFlight", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Flight> addFlight(@RequestBody NewFlight newFlight) throws ParseException  {
		System.out.println(newFlight);
		ArrayList<Flight> flightList = flightServices.getFlightList();
		Flight f = new Flight();
		f.setStartDestination(new Destination(newFlight.getStartDestinationName()));
		f.setFinalDestination(new Destination(newFlight.getFinalDestinationName()));
		DateFormat format = new SimpleDateFormat("dd/mm/yyyy hh:mm");
		f.setTakeOffDate(format.parse(newFlight.getTakeOffDate()));
		f.setLandingDate(format.parse(newFlight.getLandingDate()));
		f.setFlightLength(newFlight.getFlightLength());
		f.setNumberOfStops(newFlight.getNumberOfStops());
		// f.setFlightTicket(new Ticket(newFlight.getFlightTicket(), f));
		flightList.add(f);
		
		return flightList;
	}
	
	@GetMapping(path="/showFlights", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Flight> showFlights()
	{
		ArrayList<Flight> flights = flightServices.getFlightList();
		return flights;
	}
	
}
