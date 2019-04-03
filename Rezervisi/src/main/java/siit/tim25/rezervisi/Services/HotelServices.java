package siit.tim25.rezervisi.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import siit.tim25.rezervisi.Beans.Hotel;
import siit.tim25.rezervisi.Repository.HotelRepository;


@Component
public class HotelServices {
	
	@Autowired
	private HotelRepository hotelRepository;
	
	public Hotel save(Hotel hotel) {
		return hotelRepository.save(hotel);
	}
	
	public List<Hotel> findAll(){
		return hotelRepository.findAll();
	}
	
	public Hotel findOne(Integer hotelID)
	{
		return hotelRepository.findOne(hotelID);
	}
	
}
