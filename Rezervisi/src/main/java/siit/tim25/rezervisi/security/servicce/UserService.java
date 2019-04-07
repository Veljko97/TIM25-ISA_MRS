package siit.tim25.rezervisi.security.servicce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Repository.UserRepository;
import siit.tim25.rezervisi.security.model.User;

@Component
public class UserService  {
	
	@Autowired
	private UserRepository userRepository;

	public User findByUsername(String username) throws UsernameNotFoundException {
		User u = userRepository.findByUsername(username);
		return u;
	}

	public User findById(Integer id) throws AccessDeniedException {
		User u = userRepository.findOne(id);
		return u;
	}

	public List<User> findAll() throws AccessDeniedException {
		List<User> result = userRepository.findAll();
		return result;
	}
}
