package siit.tim25.rezervisi.Repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import siit.tim25.rezervisi.Beans.Flight;
import siit.tim25.rezervisi.Beans.FlightClass;
import siit.tim25.rezervisi.Beans.FlightType;


public interface FlightRepository extends JpaRepository<Flight, Integer> {

	@Query("select f from Flight f where f.airLine.airLineID = :id")
	public Page<Flight> findAllByAirLineId(@Param("id") Integer airlineId, Pageable pageable);
	
	@Query("SELECT f from Flight f inner join f.startDestination sd inner join f.finalDestination fd inner join f.airLine a where a.airLineName like %?9% and sd.destinationName like %?3% and fd.destinationName like %?4% and (?5 is null or f.takeOffDate >= ?5) and (?6 is null or f.landingDate <= ?6) and f.type = ?1 and f.flightClass = ?2 and (?7 is null or f.ticketPrice > ?7) and (?8 is null or f.ticketPrice < ?8) and (?10 is null or f.flightLength = ?10)")
	public Page<Flight> search(FlightType type, FlightClass flightClass, String from, String to, Date takeOff, Date landing, Double priceFrom, Double priceTo, String airLineName, String flightLength, Pageable pageable);
}
