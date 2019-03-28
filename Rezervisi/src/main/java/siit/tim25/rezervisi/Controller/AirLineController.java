package siit.tim25.rezervisi.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.Services.AirLineServices;

@RestController
@RequestMapping(path="app/airlines")
public class AirLineController {
	
	@Autowired
	private AirLineServices airLineServices;
	
	@RequestMapping(method = RequestMethod.POST,path="/addAirline", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AirLine> addAirline(@RequestBody AirLine airline)  {
		
		if(airLineServices.findOneByAirLineName(airline.getAirLineName()) != null)	{
			return new ResponseEntity<AirLine>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<AirLine>(airLineServices.save(airline),HttpStatus.OK);
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
	public ResponseEntity<AirLine> editAirline(HttpServletRequest request, @RequestBody AirLine modifiedAirline)
	{
		modifiedAirline.setAirLineID(Integer.parseInt(request.getParameter("id")));
		AirLine air = airLineServices.findOne(Integer.parseInt(request.getParameter("id")));
		modifiedAirline.setAirLineAverageGrade(air.getAirLineAverageGrade());
		modifiedAirline.setAirlineEarning(air.getAirlineEarning());
		return new ResponseEntity<AirLine>(airLineServices.save(modifiedAirline), HttpStatus.OK);
	}
	
}
