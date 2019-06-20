package siit.tim25.rezervisi.Repository;

import java.util.Date;
import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import siit.tim25.rezervisi.Beans.RoomReservation;
import siit.tim25.rezervisi.Beans.TicketStatus;
import siit.tim25.rezervisi.DTO.RoomReportDTO;
import siit.tim25.rezervisi.DTO.RoomReservationDTO;

public interface RoomReservationRepository extends JpaRepository<RoomReservation, Integer> {
	
	@Query("SELECT rr FROM RoomReservation rr WHERE rr.room.hotel.hotelID = :hotelID AND rr.status = :status")
	public Page<RoomReservation> findAllByStatus(@Param("hotelID") Integer hotelID, 
												 @Param("status") TicketStatus status,Pageable pageable);
	
	@Query("SELECT rr FROM RoomReservation rr WHERE rr.Id = :id")
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public RoomReservation lockReservation(@Param("id") Integer id);
	
	@Query("SELECT new siit.tim25.rezervisi.DTO.RoomReportDTO(rr, SUM (rr.price), week(rr.made)) from RoomReservation rr inner join rr.room r inner join r.hotel h WHERE h.hotelID = ?1 GROUP BY week(rr.made)")
	public List<RoomReportDTO> getWeeklyReport(Integer roomId);
	
	@Query("SELECT new siit.tim25.rezervisi.DTO.RoomReportDTO(rr, SUM (rr.price), rr.made) from RoomReservation rr inner join rr.room r inner join r.hotel h WHERE h.hotelID = ?1 GROUP BY month(rr.made)")
	public List<RoomReportDTO> getMonthlyReport(Integer roomId);
	
	@Query("SELECT new siit.tim25.rezervisi.DTO.RoomReportDTO(rr, SUM (rr.price), rr.made) from RoomReservation rr inner join rr.room r inner join r.hotel h WHERE h.hotelID = ?1 GROUP BY day(rr.made)")
	public List<RoomReportDTO> getDailyReport(Integer roomId);
	
	@Query("SELECT sum(rr.price) from RoomReservation rr inner join rr.room r inner join r.hotel h WHERE h.hotelID = ?1 and (?2 is null or rr.made >= ?2) and (?3 is null or rr.made <= ?3)")
	public Double getDatesReport(Integer roomId, Date startDate, Date endDate);
	
	@Query("SELECT new siit.tim25.rezervisi.DTO.RoomReservationDTO(rr) from RoomReservation rr inner join rr.user u WHERE u.id = ?1 and rr.status = '0'")
	public List<RoomReservationDTO> findRoomReservationsByUserId(Integer userId);
	
}
