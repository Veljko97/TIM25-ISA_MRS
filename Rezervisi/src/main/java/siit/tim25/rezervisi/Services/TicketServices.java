package siit.tim25.rezervisi.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.Flight;
import siit.tim25.rezervisi.Beans.Ticket;
import siit.tim25.rezervisi.Beans.TicketStatus;

import siit.tim25.rezervisi.DTO.ReservationUserDTO;
import siit.tim25.rezervisi.DTO.TicketDTO;
import siit.tim25.rezervisi.DTO.TicketReportDTO;
import siit.tim25.rezervisi.Repository.FlightRepository;

import siit.tim25.rezervisi.Repository.TicketRepository;
import siit.tim25.rezervisi.Repository.users.StandardUserRepository;
import siit.tim25.rezervisi.security.model.User;

@Component
public class TicketServices {
	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private StandardUserRepository userRepository;
	
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

	public List<TicketReportDTO> getDailyReport(Integer airlineId) {
		return ticketRepository.getDailyReport(airlineId);
	}
	
	public List<TicketReportDTO> getWeeklyReport(Integer airlineId) {
		return ticketRepository.getWeeklyReport(airlineId);
	}
	
	public List<TicketReportDTO> getMonthlyReport(Integer airlineId) {
		return ticketRepository.getMonthlyReport(airlineId);
	}
	
	public Double getDatesReport(Integer airlineId, Date startDate, Date endDate) {
		return ticketRepository.getDatesReport(airlineId, startDate, endDate);
	}
	
	public List<TicketDTO> findTicketsByUserId(Integer userId) {
		return ticketRepository.findTicketsByUserId(userId);
	}
	

	public void deleteTicket(Integer flightId, Integer ticketId) {
		Flight f = flightRepository.findOne(flightId);
		Ticket ticket = null;
		for(Ticket t: f.getFlightTickets()) {
			if (t.getIdTicket() == ticketId) {
				ticket = t;
			}
		}
		ticket.setFlight(null);
		ticket.setAirLine(null);
		ticket.setUser(null);
		f.getFlightTickets().remove(ticket);
		flightRepository.save(f);
		ticketRepository.delete(ticket);
	}
	
	public Integer addTicket(Integer airlineId, Integer flightId, ReservationUserDTO userDto, User user) {
		Flight f = this.flightRepository.findFlight(flightId);
		boolean hasEmail = userDto.getEmail() != null && !userDto.getEmail().equals("");
		Ticket t = new Ticket(f.getTicketPrice(), userDto.getSeat(), user.getFirstName(), user.getLastName(), userDto.getPassport(), userDto.getStatus(), f, hasEmail ? userDto.getEmail() : user.getEmail(), f.getAirLine());
		t.setUser(userRepository.getOne(user.getId()));
		f.getFlightTickets().add(t);
		Ticket newTicket = ticketRepository.save(t);
		return newTicket.getIdTicket();
	}
	
	public Integer addTicket(Integer airlineId, Integer flightId, ReservationUserDTO user) {
		Flight f = this.flightRepository.findFlight(flightId);
		Ticket t = new Ticket(f.getTicketPrice(), user.getSeat(), user.getFirstName(), user.getLastName(), user.getPassport(), user.getStatus(), f, user.getEmail(), f.getAirLine());
		f.getFlightTickets().add(t);
		Ticket newTicket = ticketRepository.save(t);
		return newTicket.getIdTicket();
	}
	
	public void changeTicketStatus(Integer ticketId, Integer userId) {
		Ticket t = ticketRepository.findOne(ticketId);
		Flight f = flightRepository.findOne(t.getFlight().getIdFlight());
		for(Ticket ticket: f.getFlightTickets()) {
			if (ticket.getIdTicket() == ticketId) {
				ticket.setStatus(TicketStatus.ACCEPTED);
				ticket.setUser(this.userRepository.findOne(userId));
			}
		}
		flightRepository.save(f);

	}
}
