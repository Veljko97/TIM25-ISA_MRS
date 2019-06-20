package siit.tim25.rezervisi.Services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siit.tim25.rezervisi.Beans.users.RentACarAdmin;
import siit.tim25.rezervisi.Repository.users.RentACarAdminRepository;

@Service
public class RentACarAdminServices {
	@Autowired
	private RentACarAdminRepository rentACarAdminRepository;
	
	public RentACarAdmin findOne(Integer id) {
		return rentACarAdminRepository.findOne(id);
	}
	
	public RentACarAdmin save(RentACarAdmin admin) {
		return rentACarAdminRepository.save(admin);
	}
	
	public boolean exists(Integer id) {
		return rentACarAdminRepository.exists(id);
	}
}
