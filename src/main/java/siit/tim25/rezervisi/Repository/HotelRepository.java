package siit.tim25.rezervisi.Repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import siit.tim25.rezervisi.Beans.Hotel;


public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	@Query("select h from Hotel h inner join h.destination d where d.destinationName like :dName or h.hotelName like :hName")
	public Page<Hotel> find(@Param("hName") String hotelName, @Param("dName") String destinationName, Pageable pageable);
	
	@Query("Select ho FROM Hotel ho WHERE ho.hotelID IN (SELECT ro.hotel.hotelID FROM ho.roomList ro WHERE ro.roomID IN (SELECT rr.room.roomID FROM ro.reservation rr WHERE rr.user.id = :userId AND rr.reservationEnd < :dateNow))")
	public Page<Hotel> findPastHotelReservations(@Param("userId") Integer userId, @Param("dateNow") Date dateNow , Pageable pageable);

}
