package siit.tim25.rezervisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import siit.tim25.rezervisi.Beans.AirPlane;


public interface AirplaneRepository extends JpaRepository<AirPlane, Integer> {

	public AirPlane findOneByName(String name);
	
}
