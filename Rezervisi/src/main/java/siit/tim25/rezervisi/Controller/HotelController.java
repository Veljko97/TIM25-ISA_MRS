package siit.tim25.rezervisi.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	public void addHotel(@RequestBody Hotel hotel)  {
		
		ArrayList<Hotel> hotelList = hotelServices.getHotelList();
		boolean exists = false;
		for(Hotel existingHotel : hotelList)
		{
			if(existingHotel.getHotelID().equals(hotel.getHotelID()))
			{
				exists = true;
				break;
			}
		}
		if(exists == false)
		{
			hotelServices.save(hotel);
		}
	}
	
	@GetMapping(path="/showHotels", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Hotel> showHotels()
	{
		ArrayList<Hotel> hotelList = hotelServices.getHotelList();
		return hotelList;
	}
	
}
