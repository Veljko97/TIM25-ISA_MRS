package siit.tim25.rezervisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import siit.tim25.rezervisi.Beans.Ticket;


public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	
}