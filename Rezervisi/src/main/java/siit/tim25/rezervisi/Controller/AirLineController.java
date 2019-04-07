package siit.tim25.rezervisi.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.Beans.AirLineDestination;
import siit.tim25.rezervisi.Beans.Destination;
import siit.tim25.rezervisi.Services.AirLineServices;

@RestController
@RequestMapping(path="app/airlines")
public class AirLineController {
	
	@Autowired
	private AirLineServices airLineServices;
	

	@RequestMapping(method = RequestMethod.POST,path="/addAirline", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<List<AirLine>> addAirline(@RequestBody AirLine airline)  {
		
		if(airLineServices.findOneByAirLineName(airline.getAirLineName()) != null)	{
			return new ResponseEntity<List<AirLine>>(HttpStatus.BAD_REQUEST);
		}
		airLineServices.save(airline);
		return new ResponseEntity<List<AirLine>>(airLineServices.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(path="/showAirLines", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AirLine>> showAirLines()
	{
		return new ResponseEntity<List<AirLine>>(airLineServices.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(path="/getAirline", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AirLine> getAirline(HttpServletRequest request)
	{
		return new ResponseEntity<AirLine>(airLineServices.findOne(Integer.parseInt(request.getParameter("index"))),HttpStatus.OK);
	}
	
	@PostMapping(path="/editAirline", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('AIRLINE_ADMIN')")
	public ResponseEntity<AirLine> editAirline(HttpServletRequest request, @RequestBody AirLine modifiedAirline)
	{
		modifiedAirline.setAirLineID(Integer.parseInt(request.getParameter("id")));
		AirLine air = airLineServices.findOne(Integer.parseInt(request.getParameter("id")));
		modifiedAirline.setAirLineAverageGrade(air.getAirLineAverageGrade());
		modifiedAirline.setAirlineEarning(air.getAirlineEarning());
		return new ResponseEntity<AirLine>(airLineServices.save(modifiedAirline), HttpStatus.OK);
	}
	
	@GetMapping(path="/showDestinations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Destination>> getDestinations(HttpServletRequest request) {
		ArrayList<Destination> responseList = new ArrayList<Destination>();
		for(AirLineDestination ad: airLineServices.findOne(Integer.parseInt(request.getParameter("id"))).getAirLineDestinations()) {
			responseList.add(ad.getDestination());
		}
		return new ResponseEntity<List<Destination>>(responseList,HttpStatus.OK);
	}
	
	@PostMapping(path="/addDestination", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('AIRLINE_ADMIN')")
	public ResponseEntity<List<Destination>> addDestination (HttpServletRequest request, @RequestBody Destination destination) {
		AirLine airline = airLineServices.findOne(Integer.parseInt(request.getParameter("id")));
		AirLineDestination airDest = new AirLineDestination(airline, destination);
		airline.getAirLineDestinations().add(airDest);
		airLineServices.save(airline);
		ArrayList<Destination> responseList = new ArrayList<Destination>();
		for(AirLineDestination ad: airline.getAirLineDestinations()) {
			responseList.add(ad.getDestination());
		}
		return new ResponseEntity<List<Destination>> (responseList, HttpStatus.OK);
	}
	
}
