package siit.tim25.rezervisi.security.servicce;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.DTO.RegistrationUserDTO;
import siit.tim25.rezervisi.DTO.UserDTO;
import siit.tim25.rezervisi.Repository.UserRepository;
import siit.tim25.rezervisi.security.model.User;

@Component
public class UserService  {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder pwEnc;
	
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
	
	public Set<User> findbyRole(String role){
		return userRepository.findbyRole(role);
	}
	
	public UserDTO create(RegistrationUserDTO t) {
		if(userRepository.findByUsername(t.getUsername())==null)
		{
			User u = new User();
			u.setFirstName(t.getFirstName());
			u.setLastName(t.getLastName());
			u.setEmail(t.getEmail());
			u.setUsername(t.getUsername());
			u.setPassword(pwEnc.encode(t.getPassword()));
			u.setEnabled(true);
			userRepository.save(u);
			
			return new UserDTO(u);
		}
		return null;
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
}
