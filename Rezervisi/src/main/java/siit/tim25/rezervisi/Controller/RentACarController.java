package siit.tim25.rezervisi.Controller;

import java.util.ArrayList;

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
	public void addHotel(@RequestBody RentACar rnt)  {
		
		ArrayList<RentACar> rentACarList = rentACarServices.getRentACarList();
		boolean exists = false;
		for(RentACar existingRentACar : rentACarList)
		{
			if(existingRentACar.getRentACarID().equals(rnt.getRentACarID()))
			{
				exists = true;
				break;
			}
		}
		if(exists == false)
		{
			rentACarServices.save(rnt);
		}
	}
	
	@GetMapping(path="/showRentACars", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<RentACar> showRentACars()
	{
		ArrayList<RentACar> rentACarList = rentACarServices.getRentACarList();
		return rentACarList;
	}
	
	@GetMapping(path="/showRentACar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RentACar> showRentACar(@PathVariable Integer id)
	{
		return new ResponseEntity<RentACar>(rentACarServices.getRentACar(id), HttpStatus.OK);
	}
	
	@PutMapping(path="/updateRentACar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> updateRentACar(@RequestBody RentACar rent){
		
		return new ResponseEntity<Boolean>(rentACarServices.update(rent), HttpStatus.OK);

	}
}
