package siit.tim25.rezervisi.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import siit.tim25.rezervisi.Beans.Hotel;
import siit.tim25.rezervisi.Repository.HotelRepository;


@Component
public class HotelServices {
	
	@Autowired
	private HotelRepository hotelRepository;
	
	public boolean save(Hotel hotel) {
		return hotelRepository.save(hotel);
	}
	
	public ArrayList<Hotel> getHotelList(){
		return hotelRepository.getHotelList();
	}
	
	public Hotel getHotel(Integer hotelID)
	{
		return hotelRepository.getHotel(hotelID);
	}
	
}
