package siit.tim25.rezervisi.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import siit.tim25.rezervisi.Beans.AirLine;


public interface AirLineRepository extends JpaRepository<AirLine, Integer> {

	public AirLine findOneByAirLineName(String airLineName);
	
	@Query("select a from AirLine a where airLineName like %?1%")
	public Page<AirLine> findByAirLineName(String airLineName, Pageable pageable);
	
}
