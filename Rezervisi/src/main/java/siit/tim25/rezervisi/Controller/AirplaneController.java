package siit.tim25.rezervisi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import siit.tim25.rezervisi.Beans.AirPlane;
import siit.tim25.rezervisi.Services.AirplaneServices;

@RestController
@RequestMapping(path="app/airplanes")
public class AirplaneController {
	
	@Autowired
	private AirplaneServices airplaneServices;
	
	
	@GetMapping(path="/show", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AirPlane>> showAirplanes()
	{
		return new ResponseEntity<List<AirPlane>>(airplaneServices.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(path="/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AirPlane> get(@PathVariable Integer id)
	{
		return new ResponseEntity<AirPlane>(airplaneServices.findOne(id),HttpStatus.OK);
	}
	
}
