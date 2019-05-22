package siit.tim25.rezervisi.Repository;

import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import siit.tim25.rezervisi.Beans.RoomReservation;
import siit.tim25.rezervisi.Beans.TicketStatus;

public interface RoomReservationRepository extends JpaRepository<RoomReservation, Integer> {
	
	@Query("SELECT rr FROM RoomReservation rr WHERE rr.room.hotel.hotelID = :hotelId AND rr.status = :status")
	public Page<RoomReservation> findAllByStatus(@Param("hotelId") Integer hotelId, 
												 @Param("status") TicketStatus status,Pageable pageable);
	
	@Query("SELECT rr FROM RoomReservation rr WHERE rr.Id = :id")
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public RoomReservation lockReservation(@Param("id") Integer id);
}
