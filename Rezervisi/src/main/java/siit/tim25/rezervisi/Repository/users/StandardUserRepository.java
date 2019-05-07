package siit.tim25.rezervisi.Repository.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import siit.tim25.rezervisi.Beans.users.StandardUser;

public interface StandardUserRepository extends JpaRepository<StandardUser, Integer>{
	
	public StandardUser findByUsername( String username );
	
	@Query("SELECT su FROM StandardUser su WHERE (su.id != :myId) and "
			+ " ((lower(su.username) LIKE lower(CONCAT(:param,'%'))) or (lower(su.firstName) LIKE lower(CONCAT(:param,'%'))) or (lower(su.lastName) LIKE lower(CONCAT(:param,'%'))))"
			+ " and (su.id NOT IN (SELECT rq.receiver.id FROM su.sentRequest rq))")
	public Page<StandardUser> findMatches(@Param("param") String param,@Param("myId") Integer myId, Pageable page);
	
}
