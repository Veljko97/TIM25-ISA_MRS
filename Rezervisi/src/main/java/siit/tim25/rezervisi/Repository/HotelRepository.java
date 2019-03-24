package siit.tim25.rezervisi.Repository;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import siit.tim25.rezervisi.Beans.Hotel;


@Component
public class HotelRepository {
	
	private ArrayList<Hotel> hotels = new ArrayList<Hotel>();
	
	public boolean save(Hotel hotel) {
		hotel.setHotelID(hotels.size());
		hotels.add(hotel);
		return true;
	}
	
	public ArrayList<Hotel> getHotelList(){
		return hotels;
	}
	
	public Hotel getHotel(Integer hotelID)
	{
		return hotels.get(hotelID);
	}
	
}
