package siit.tim25.rezervisi.Services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siit.tim25.rezervisi.Beans.users.HotelAdmin;
import siit.tim25.rezervisi.Repository.users.HotelAdminRepository;

@Service
public class HotelAdminServices {
	@Autowired
	private HotelAdminRepository hotelAdminRepository;
	
	public HotelAdmin findOne(Integer id) {
		return hotelAdminRepository.findOne(id);
	}
	
	public HotelAdmin save(HotelAdmin admin) {
		return hotelAdminRepository.save(admin);
	}
	
	public boolean exists(Integer id) {
		return hotelAdminRepository.exists(id);
	}
}
