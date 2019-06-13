package siit.tim25.rezervisi.Repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import siit.tim25.rezervisi.Beans.AirLine;


public interface AirLineRepository extends JpaRepository<AirLine, Integer> {

	public AirLine findOneByAirLineName(String airLineName);
	
	@Query("select a from AirLine a where airLineName like %?1%")
	public Page<AirLine> findByAirLineName(String airLineName, Pageable pageable);

	@Query("Select al FROM AirLine al WHERE al.airLineID IN (SELECT fl.airLine.airLineID FROM al.airLineFlights fl WHERE fl.landingDate < :dateNow AND fl.idFlight IN (SELECT ti.flight.idFlight FROM fl.flightTickets ti WHERE ti.user.id = :userId))")
	public Page<AirLine> findPastAirLineReservations(@Param("userId") Integer userId, @Param("dateNow") Date dateNow , Pageable pageable);

}
