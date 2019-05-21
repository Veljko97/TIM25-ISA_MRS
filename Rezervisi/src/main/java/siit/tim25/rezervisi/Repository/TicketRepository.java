package siit.tim25.rezervisi.Repository;


import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import siit.tim25.rezervisi.Beans.Ticket;
import siit.tim25.rezervisi.Beans.TicketStatus;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import siit.tim25.rezervisi.Beans.Ticket;
import siit.tim25.rezervisi.DTO.TicketDTO;
import siit.tim25.rezervisi.DTO.TicketReportDTO;



public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	@Query("SELECT t FROM Ticket t WHERE t.idTicket = :id")
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public Ticket lockTicket(@Param("id") Integer id);
	
	@Query("SELECT t FROM Ticket t WHERE t.airLine.airLineID = :airlineId and t.status = :status")
	public Page<Ticket> findAllByStatus(@Param("airlineId") Integer airlineId, @Param("status") TicketStatus status, Pageable pageable);

	@Query("SELECT new siit.tim25.rezervisi.DTO.TicketReportDTO(t, SUM (t.ticketPrice), week(t.created)) from Ticket t inner join t.airLine a WHERE a.airLineID = ?1 GROUP BY week(t.created)")
	public List<TicketReportDTO> getWeeklyReport(Integer airlineId);
	
	@Query("SELECT new siit.tim25.rezervisi.DTO.TicketReportDTO(t, SUM (t.ticketPrice), t.created) from Ticket t inner join t.airLine a WHERE a.airLineID = ?1 GROUP BY month(t.created)")
	public List<TicketReportDTO> getMonthlyReport(Integer airlineId);
	
	@Query("SELECT new siit.tim25.rezervisi.DTO.TicketReportDTO(t, SUM (t.ticketPrice), t.created) from Ticket t inner join t.airLine a WHERE a.airLineID = ?1 GROUP BY day(t.created)")
	public List<TicketReportDTO> getDailyReport(Integer airlineId);
	
	@Query("SELECT sum(t.ticketPrice) from Ticket t inner join t.airLine a WHERE a.airLineID = ?1 and (?2 is null or t.created >= ?2) and (?3 is null or t.created <= ?3)")
	public Double getDatesReport(Integer airlineId, Date startDate, Date endDate);
	
	@Query("SELECT new siit.tim25.rezervisi.DTO.TicketDTO(t) from Ticket t inner join t.user u WHERE u.id = ?1 and t.status = '0'")
	public List<TicketDTO> findTicketsByUserId(Integer userId);

}
