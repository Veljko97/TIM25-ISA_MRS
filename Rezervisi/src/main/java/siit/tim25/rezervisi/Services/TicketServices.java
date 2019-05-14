package siit.tim25.rezervisi.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.Ticket;
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
}
