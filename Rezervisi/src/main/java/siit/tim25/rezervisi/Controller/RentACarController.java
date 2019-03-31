package siit.tim25.rezervisi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import siit.tim25.rezervisi.Beans.RentACar;
import siit.tim25.rezervisi.Services.RentACarServices;


@RestController
@RequestMapping(path="app/rentacar")
public class RentACarController {
	
	@Autowired
	private RentACarServices rentACarServices;
	
	@PostMapping(path="/addRentACar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RentACar>> addRentACar(@RequestBody RentACar rnt)  {
		if(rentACarServices.findOneByRentACarName(rnt.getRentACarName()) != null) {
			return new ResponseEntity<List<RentACar>>(HttpStatus.BAD_REQUEST);
		}
		rentACarServices.save(rnt);
		return new ResponseEntity<List<RentACar>>(rentACarServices.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(path="/showRentACars", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RentACar>> showRentACars()
	{
		return new ResponseEntity<List<RentACar>>(rentACarServices.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path="/showRentACar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RentACar> showRentACar(@PathVariable Integer id)
	{
		return new ResponseEntity<RentACar>(rentACarServices.findOne(id), HttpStatus.OK);
	}
	
	@PutMapping(path="/updateRentACar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RentACar> updateRentACar(@RequestBody RentACar rent){
		
		return new ResponseEntity<RentACar>(rentACarServices.update(rent), HttpStatus.OK);

	}
}
