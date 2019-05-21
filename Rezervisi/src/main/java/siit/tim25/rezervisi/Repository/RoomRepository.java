package siit.tim25.rezervisi.Repository;

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
	
}
