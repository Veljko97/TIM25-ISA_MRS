package siit.tim25.rezervisi.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import siit.tim25.rezervisi.Beans.Hotel;


public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	@Query("select h from Hotel h inner join h.destination d where d.destinationName like :dName and h.hotelName like :hName")
	public Page<Hotel> find(@Param("hName") String hotelName, @Param("dName") String destinationName, Pageable pageable);
}
