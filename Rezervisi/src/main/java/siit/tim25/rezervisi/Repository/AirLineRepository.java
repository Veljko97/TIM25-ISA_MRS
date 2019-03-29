package siit.tim25.rezervisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import siit.tim25.rezervisi.Beans.AirLine;


public interface AirLineRepository extends JpaRepository<AirLine, Integer> {

	public AirLine findOneByAirLineName(String airLineName);
	
}
