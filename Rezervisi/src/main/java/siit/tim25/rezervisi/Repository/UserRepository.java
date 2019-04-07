package siit.tim25.rezervisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import siit.tim25.rezervisi.security.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername( String username );
}
