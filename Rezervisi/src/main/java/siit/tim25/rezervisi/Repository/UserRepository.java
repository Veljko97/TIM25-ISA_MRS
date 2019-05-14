package siit.tim25.rezervisi.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import siit.tim25.rezervisi.security.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername( String username );
	
	@Query("SELECT u FROM User u WHERE :role IN (SELECT auth.name FROM u.authorities auth)")
	Set<User> findbyRole(@Param("role") String userRole);
		
	@Query("SELECT u FROM User u WHERE :email = u.email")
	User findByEmail(@Param("email") String email);
	
}
