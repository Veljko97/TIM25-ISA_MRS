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


public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	@Query("SELECT t FROM Ticket t WHERE t.idTicket = :id")
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public Ticket lockTicket(@Param("id") Integer id);
	
	@Query("SELECT t FROM Ticket t WHERE t.airLine.airLineID = :airlineId and t.status = :status")
	public Page<Ticket> findAllByStatus(@Param("airlineId") Integer airlineId, @Param("status") TicketStatus status, Pageable pageable);
}
