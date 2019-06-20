package siit.tim25.rezervisi.Services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siit.tim25.rezervisi.Repository.users.AuthorityRepository;
import siit.tim25.rezervisi.security.model.Authority;

@Service
public class AuthorityServices {
	@Autowired
	private AuthorityRepository authorityRepository;
	
	public Authority findOne(Integer id) {
		return authorityRepository.findOne(id);
	}
}
