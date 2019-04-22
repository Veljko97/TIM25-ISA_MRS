package siit.tim25.rezervisi.Repository.users;

import org.springframework.data.jpa.repository.JpaRepository;

import siit.tim25.rezervisi.Beans.users.StandardUser;

public interface StandardUserRepository extends JpaRepository<StandardUser, Integer>{

}
