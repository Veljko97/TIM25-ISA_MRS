package siit.tim25.rezervisi.Services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import siit.tim25.rezervisi.Beans.users.StandardUser;
import siit.tim25.rezervisi.Repository.users.StandardUserRepository;

@Controller("StandardUserServices")
public class StandardUserServices {
	@Autowired
	private StandardUserRepository userRepository;
	
	public StandardUser save(StandardUser user) {
		return userRepository.save(user);
	}
	
	public StandardUser findByUsername( String username ) {
		return userRepository.findByUsername(username);
	}
	
	public StandardUser findOne(Integer id) {
		return userRepository.findOne(id);
	}
	
	public boolean exists(Integer id) {
		return userRepository.exists(id);
	}
	
	public Page<StandardUser> findMatch(String param, Integer myId, Pageable page) {
		return userRepository.findMatches(param, myId, page);
	}
	
}
