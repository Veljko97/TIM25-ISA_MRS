package siit.tim25.rezervisi.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import siit.tim25.rezervisi.Beans.DiscountPoint;
import siit.tim25.rezervisi.Beans.Flight;
import siit.tim25.rezervisi.Beans.Room;
import siit.tim25.rezervisi.Beans.RoomReservation;
import siit.tim25.rezervisi.Beans.Ticket;
import siit.tim25.rezervisi.Beans.TicketStatus;
import siit.tim25.rezervisi.Beans.Vehicle;
import siit.tim25.rezervisi.Beans.VehicleReservation;
import siit.tim25.rezervisi.Beans.users.StandardUser;
import siit.tim25.rezervisi.DTO.FriendsDTO;
import siit.tim25.rezervisi.DTO.FriendsListsDTO;
import siit.tim25.rezervisi.DTO.ReservationIdsDTO;
import siit.tim25.rezervisi.DTO.ReservationUserDTO;
import siit.tim25.rezervisi.DTO.TicketDTO;
import siit.tim25.rezervisi.DTO.TicketReportDTO;
import siit.tim25.rezervisi.Repository.FlightRepository;
import siit.tim25.rezervisi.Repository.TicketRepository;
import siit.tim25.rezervisi.Repository.users.StandardUserRepository;
import siit.tim25.rezervisi.Services.users.StandardUserServices;
import siit.tim25.rezervisi.security.model.User;
import siit.tim25.rezervisi.security.servicce.UserService;

@Component
public class TicketServices {
	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private StandardUserRepository userRepository;
	
	@Autowired
	private StandardUserServices stdUserServices;
	
	@Autowired
	private UserService userServices;
	
	@Autowired
	private ProducerServices producerServices;
	
	@Autowired
	private VehicleReservationServices vrServices;
	
	@Autowired
	private DiscountPointsServices dpServices;
	
	@Autowired
	private RoomReservationServices rrServices;
	
	
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
		System.out.println("1");
		System.out.println(userDto);
		System.out.println(user);
		Ticket t = new Ticket(f.getPrice(userDto.getFlightClass()), userDto.getSeat(), user.getFirstName(), user.getLastName(), userDto.getPassport(), userDto.getStatus(), f, hasEmail ? userDto.getEmail() : user.getEmail(), f.getAirLine(), userDto.getFlightClass());
		t.setUser(userRepository.getOne(user.getId()));
		f.getFlightTickets().add(t);
		Ticket newTicket = ticketRepository.save(t);
		return newTicket.getIdTicket();
	}
	
	public Integer addTicket(Integer airlineId, Integer flightId, ReservationUserDTO user) {
		Flight f = this.flightRepository.findFlight(flightId);
		System.out.println("2");
		System.out.println(user);
		Ticket t = new Ticket(f.getPrice(user.getFlightClass()), user.getSeat(), user.getFirstName(), user.getLastName(), user.getPassport(), user.getStatus(), f, user.getEmail(), f.getAirLine(), user.getFlightClass());
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
	
	@Transactional
	public ResponseEntity<Integer> buyTicket(ReservationUserDTO user, StandardUser loggedUser, Integer airlineId, Integer flightId, String username) {
		loggedUser.setDiscauntPoints(loggedUser.getDiscauntPoints() + 1);
		stdUserServices.save(loggedUser);
		switch(user.getUserType()) {
		case CURRENT:		    
			return new ResponseEntity<Integer>(this.addTicket(airlineId, flightId, user, loggedUser), HttpStatus.OK);
		case UNREGISTERED:
			User registered = userServices.findByEmail(user.getEmail());
			if (registered == null) {
				return new ResponseEntity<Integer>(this.addTicket(airlineId, flightId, user), HttpStatus.OK);
			} else {
				return new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
			}
		case REGISTERED:
			StandardUser standardLoggedUser = stdUserServices.findByUsername(username);
			FriendsListsDTO friends = new FriendsListsDTO(standardLoggedUser);
			User registeredUser = userServices.findByEmail(user.getEmail());
			for (FriendsDTO f: friends.getAccepted()) {
				if (f.getOther().getEmail().equals(user.getEmail())) {
					return new ResponseEntity<Integer>(this.addTicket(airlineId, flightId, user, registeredUser), HttpStatus.OK);
				}
			}
		default:
			return new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
		}
	}
	
	public void sendEmailInvitations(List<Integer> ids, User loggedUser, String host) {
		for(Integer id: ids) {
			Ticket t = this.findOne(id);
			if (!t.getEmail().equals(loggedUser.getEmail())) {
				String text = "You have a new invitation for a trip with your friend " + 
						loggedUser.getFirstName() + " " + loggedUser.getLastName() + " on Reservify platform. Please follow this link to proceed: "+
						host+"/invitation/flight.html?ticketId=" + t.getIdTicket();
				this.producerServices.sendEmailTo("Invitation for trip on Reservify!", t.getEmail(), text);
			}
		}
	}
	
	public void sendFinishReservationEmail(ReservationIdsDTO ids, StandardUser loggedUser, String host) {
		DiscountPoint dp = this.dpServices.findByMyPoints(loggedUser.getDiscauntPoints());
		
		if(dp.getId().equals(-1)) {
			ids.setUsePoints(false);
		}
		
		Ticket t = this.findOne(ids.getTicketId());
		String text = "Your reservation is successful on Reservify platform. You reserved flight from " 
				+ t.getFlight().getStartDestination().getDestinationName() + " to " + t.getFlight().getFinalDestination().getDestinationName() + ".";
		Integer countPoints = 0;
		if(ids.getUsePoints()) {
			Double pr = t.getTicketPrice();
			t.setTicketPrice(pr - (pr * (dp.getDiscountPercent() / 100)));
			this.save(t);
		}
		if (ids.getRoomIds().length > 0 || ids.getVehicleIds().length > 0) {
			text += "Also, you made some additional reservations: ";
			for(Integer id: ids.getRoomIds()) {
				RoomReservation rr = rrServices.findOne(id);
				if(ids.getUsePoints()) {
					Double pr = rr.getPrice();
					rr.setPrice(pr - (pr * (dp.getDiscountPercent() / 100)));
					rrServices.save(rr);
				}
				Room r = rr.getRoom();
				countPoints += 1;
				text += "Room in " + r.getHotel().getHotelName() + " hotel, room capacity: " + r.getRoomCapacity() + ". \n";
			}
			
			for(Integer id: ids.getVehicleIds()) {
				VehicleReservation vr = vrServices.findOne(id);
				if(ids.getUsePoints()) {
					Double pr = vr.getPrice();
					vr.setPrice(pr - (pr * (dp.getDiscountPercent() / 100)));
					vrServices.save(vr);
				}
				Vehicle v = vr.getVehicle();
				countPoints += 1;
				text += "Vehicle " + v.getVehicleName() + " from " + v.getBranch().getService().getRentACarName() + " rent-a-car service.\n";
				
			}
		}
		if(ids.getUsePoints()) {
			loggedUser.setDiscauntPoints(loggedUser.getDiscauntPoints() - dp.getPointsNeeded());
		}else {
			loggedUser.setDiscauntPoints(loggedUser.getDiscauntPoints() + countPoints);
		}
		stdUserServices.save(loggedUser);
		text += "\n You Have: "+ loggedUser.getDiscauntPoints()+" discount points on Reservify ";
		text += "\n Please follow this link to confirem your flight ticket: \n" + 
		host +"/invitation/flight.html?ticketId=" + t.getIdTicket();
		this.producerServices.sendEmailTo("Successful reservation on Reservify!", loggedUser.getEmail(), text);
	}
	
	@Transactional
	public boolean makeFastTicket(Integer ticketId, StandardUser loggedUser, ReservationUserDTO res) {
		Ticket ticket = this.lockTicket(ticketId);
		
		if(ticket.getStatus().getValue() != 3) {
			return false;
		}
		
		ticket.setEmail(loggedUser.getEmail());
		ticket.setFirstName(loggedUser.getFirstName());
		ticket.setLastName(loggedUser.getLastName());
		ticket.setPassport(res.getPassport());
		ticket.setStatus(TicketStatus.ACCEPTED);
		ticket.setUser(loggedUser);
		this.save(ticket);
		
		return true;
	}
}
