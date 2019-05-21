package siit.tim25.rezervisi.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.Ticket;
import siit.tim25.rezervisi.Beans.TicketStatus;
import siit.tim25.rezervisi.Repository.TicketRepository;

@Component
public class TicketServices {
	@Autowired
	private TicketRepository ticketRepository;
	
	public List<Ticket> findAll(){
		return ticketRepository.findAll();
	}
	
	public Ticket findOne(Integer ticketId)
	{
		return ticketRepository.findOne(ticketId);
	}
	
	public Ticket lockTicket(Integer id) {
		return ticketRepository.lockTicket(id);
	}
	
	public Ticket save(Ticket ticket) {
		return ticketRepository.save(ticket);
	}
	
	public Page<Ticket> findAllByStatus(Integer airlineId,TicketStatus status, Pageable pageable){
		return ticketRepository.findAllByStatus(airlineId, status, pageable);
	}
}
