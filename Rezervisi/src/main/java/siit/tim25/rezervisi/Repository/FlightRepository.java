package siit.tim25.rezervisi.Repository;

import java.util.Date;

import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import siit.tim25.rezervisi.Beans.Flight;
import siit.tim25.rezervisi.Beans.FlightClass;
import siit.tim25.rezervisi.Beans.FlightType;


public interface FlightRepository extends JpaRepository<Flight, Integer> {

	@Query("select f from Flight f where f.airLine.airLineID = :id")
	public Page<Flight> findAllByAirLineId(@Param("id") Integer airlineId, Pageable pageable);
	
	@Query("SELECT f from Flight f inner join f.startDestination sd inner join f.finalDestination fd inner join f.airLine a where a.airLineName like %?9% and sd.destinationName like %?3% and fd.destinationName like %?4% and (?5 is null or f.takeOffDate >= ?5) and (?6 is null or f.landingDate <= ?6) and (?1 is null or f.type = ?1) and (?2 is null or f.airplane.numberOfFirstClassSeats > (SELECT count(t) FROM Ticket t WHERE t.flightClass = ?2 and t.flight.idFlight = f.idFlight)) and (?7 is null or f.firstClassPrice > ?7) and (?8 is null or f.firstClassPrice < ?8) and (?10 is null or f.flightLength = ?10) and (?12 is null or f.airplane.luggage > ?12) and (?11 is null or ((f.airplane.numberOfFirstClassSeats - ?11) >= ((SELECT count(t) FROM Ticket t WHERE t.flightClass = ?2 and t.flight.idFlight = f.idFlight))))")
	public Page<Flight> searchFirstClass(FlightType type, FlightClass flightClass, String from, String to, Date takeOff, Date landing, Double priceFrom, Double priceTo, String airLineName, String flightLength, Integer numberOfPeople, Double luggage, Pageable pageable);
	
	@Query("SELECT f from Flight f inner join f.startDestination sd inner join f.finalDestination fd inner join f.airLine a where a.airLineName like %?9% and sd.destinationName like %?3% and fd.destinationName like %?4% and (?5 is null or f.takeOffDate >= ?5) and (?6 is null or f.landingDate <= ?6) and (?1 is null or f.type = ?1) and (?2 is null or f.airplane.numberOfEconomyClassSeats > (SELECT count(t) FROM Ticket t WHERE t.flightClass = ?2 and t.flight.idFlight = f.idFlight)) and (?7 is null or f.economyClassPrice > ?7) and (?8 is null or f.economyClassPrice < ?8) and (?10 is null or f.flightLength = ?10) and (?12 is null or f.airplane.luggage > ?12) and (?11 is null or ((f.airplane.numberOfEconomyClassSeats - ?11) >= ((SELECT count(t) FROM Ticket t WHERE t.flightClass = ?2 and t.flight.idFlight = f.idFlight))))")
	public Page<Flight> searchEconomyClass(FlightType type, FlightClass flightClass, String from, String to, Date takeOff, Date landing, Double priceFrom, Double priceTo, String airLineName, String flightLength, Integer numberOfPeople, Double luggage, Pageable pageable);
	
	@Query("SELECT f from Flight f inner join f.startDestination sd inner join f.finalDestination fd inner join f.airLine a where a.airLineName like %?9% and sd.destinationName like %?3% and fd.destinationName like %?4% and (?5 is null or f.takeOffDate >= ?5) and (?6 is null or f.landingDate <= ?6) and (?1 is null or f.type = ?1) and (?2 is null or f.airplane.numberOfBusinessClassSeats > (SELECT count(t) FROM Ticket t WHERE t.flightClass = ?2 and t.flight.idFlight = f.idFlight)) and (?7 is null or f.businessClassPrice > ?7) and (?8 is null or f.businessClassPrice < ?8) and (?10 is null or f.flightLength = ?10) and (?12 is null or f.airplane.luggage > ?12) and (?11 is null or ((f.airplane.numberOfBusinessClassSeats - ?11) >= ((SELECT count(t) FROM Ticket t WHERE t.flightClass = ?2 and t.flight.idFlight = f.idFlight))))")
	public Page<Flight> searchBusinessClass(FlightType type, FlightClass flightClass, String from, String to, Date takeOff, Date landing, Double priceFrom, Double priceTo, String airLineName, String flightLength, Integer numberOfPeople, Double luggage, Pageable pageable);
	
	@Query("SELECT f from Flight f where f.idFlight = :id")
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public Flight findFlight(@Param("id") Integer id);
	
	@Query("Select fl FROM Flight fl WHERE fl.landingDate < :dateNow AND fl.idFlight IN (SELECT ti.flight.idFlight FROM fl.flightTickets ti WHERE ti.user.id = :userId)")
	public Page<Flight> findPastFlightReservations(@Param("userId") Integer userId, @Param("dateNow") Date dateNow , Pageable pageable);

}
