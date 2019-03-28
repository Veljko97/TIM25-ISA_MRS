package siit.tim25.rezervisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import siit.tim25.rezervisi.Beans.Flight;


public interface FlightRepository extends JpaRepository<Flight, Integer> {

}
