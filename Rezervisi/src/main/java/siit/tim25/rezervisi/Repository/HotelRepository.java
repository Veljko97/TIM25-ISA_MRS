package siit.tim25.rezervisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import siit.tim25.rezervisi.Beans.Hotel;


public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	
}
