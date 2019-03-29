package siit.tim25.rezervisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import siit.tim25.rezervisi.Beans.Vehicle;


public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
	
}
