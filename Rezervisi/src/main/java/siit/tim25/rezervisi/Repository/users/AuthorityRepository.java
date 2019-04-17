package siit.tim25.rezervisi.Repository.users;

import org.springframework.data.jpa.repository.JpaRepository;

import siit.tim25.rezervisi.security.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer>{
}
