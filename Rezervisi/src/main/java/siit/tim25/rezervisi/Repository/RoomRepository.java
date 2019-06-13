package siit.tim25.rezervisi.Repository;

import java.util.Date;

import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import siit.tim25.rezervisi.Beans.Room;


public interface RoomRepository extends JpaRepository<Room, Integer> {

	public Room findOneByRoomNumber(String roomNumber);
	
	@Query("select r from Room r where r.hotel.hotelID = :id")
	public Page<Room> findAllByHotelID(@Param("id") Integer hotelId, Pageable pageable);
	
	@Query("SELECT r FROM Room r WHERE r.roomID = :id")
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public Room lockRoom(@Param("id") Integer id);
	
	@Query("SELECT r FROM Room r WHERE r.hotel.hotelID = :hotelId AND 0 = (SELECT count(rr) FROM RoomReservation rr WHERE rr.room.roomID = r.roomID AND ((rr.reservationStart <= :startDate AND rr.reservationEnd >= :startDate) OR (rr.reservationStart <= :endDate AND rr.reservationEnd >= :endDate) OR (rr.reservationStart >= :startDate AND rr.reservationEnd <= :endDate)))")
	public Page<Room> findFree(@Param("hotelId") Integer hotelId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

	@Query("Select ro FROM Room ro WHERE ro.roomID IN (SELECT rr.room.roomID FROM ro.reservation rr WHERE rr.user.id = :userId AND rr.reservationEnd < :dateNow)")
	public Page<Room> findPastRoomReservations(@Param("userId") Integer userId, @Param("dateNow") Date dateNow , Pageable pageable);

}
