package siit.tim25.rezervisi.Services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siit.tim25.rezervisi.Beans.users.AirLineAdmin;
import siit.tim25.rezervisi.Repository.users.AirLineAdminRepository;

@Service
public class AirLineAdminServices {
	@Autowired
	private AirLineAdminRepository airLineAdminRepository;
	
	public AirLineAdmin findOne(Integer id) {
		return airLineAdminRepository.findOne(id);
	}
	
	public AirLineAdmin save(AirLineAdmin admin) {
		return airLineAdminRepository.save(admin);
	}
	
	public boolean exists(Integer id) {
		return airLineAdminRepository.exists(id);
	}
}
