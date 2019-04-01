package siit.tim25.rezervisi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import siit.tim25.rezervisi.Beans.Hotel;
import siit.tim25.rezervisi.Services.HotelServices;



@RestController
@RequestMapping(path="app/hotels")
public class HotelController {
	

	@Autowired
	private HotelServices hotelServices;
	
	@PostMapping(path="/addHotel", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Hotel>> addHotel(@RequestBody Hotel hotel)  {	
		hotelServices.save(hotel);
		return new ResponseEntity<List<Hotel>>(hotelServices.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(path="/showHotels", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Hotel>> showHotels()
	{
		return new ResponseEntity<List<Hotel>>(hotelServices.findAll(),HttpStatus.OK);
	}
	
}
