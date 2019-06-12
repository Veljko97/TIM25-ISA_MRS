package siit.tim25.rezervisi.Controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.Beans.Destination;
import siit.tim25.rezervisi.Beans.Flight;
import siit.tim25.rezervisi.Beans.InvitationResponseType;
import siit.tim25.rezervisi.Beans.Room;
import siit.tim25.rezervisi.Beans.Ticket;
import siit.tim25.rezervisi.Beans.TicketStatus;
import siit.tim25.rezervisi.Beans.Vehicle;
import siit.tim25.rezervisi.Beans.users.AirLineAdmin;
import siit.tim25.rezervisi.Beans.users.StandardUser;
import siit.tim25.rezervisi.DTO.FlightDTO;
import siit.tim25.rezervisi.DTO.FriendsDTO;
import siit.tim25.rezervisi.DTO.FriendsListsDTO;
import siit.tim25.rezervisi.DTO.ReservationIdsDTO;
import siit.tim25.rezervisi.DTO.ReservationUserDTO;
import siit.tim25.rezervisi.DTO.TicketDTO;
import siit.tim25.rezervisi.DTO.UserDTO;
import siit.tim25.rezervisi.Services.AirLineServices;
import siit.tim25.rezervisi.Services.AirplaneServices;
import siit.tim25.rezervisi.Services.DestinationServices;
import siit.tim25.rezervisi.Services.FlightServices;
import siit.tim25.rezervisi.Services.ImageServices;
import siit.tim25.rezervisi.Services.ProducerServices;
import siit.tim25.rezervisi.Services.RoomServices;
import siit.tim25.rezervisi.Services.TicketServices;
import siit.tim25.rezervisi.Services.VehicleServices;
import siit.tim25.rezervisi.Services.users.AuthorityServices;
import siit.tim25.rezervisi.Services.users.StandardUserServices;
import siit.tim25.rezervisi.security.TokenUtils;
import siit.tim25.rezervisi.security.model.TokenState;
import siit.tim25.rezervisi.security.model.User;
import siit.tim25.rezervisi.security.servicce.UserService;

@RestController
@RequestMapping(path="app/airlines")
public class AirLineController {
	
	@Autowired
	private AirLineServices airLineServices;
	
	@Autowired
	private DestinationServices destinationServices;
	
	@Autowired
	private FlightServices flightServices;
	
	@Autowired
	private AirplaneServices airplaneServices;
	
	@Autowired
	private ProducerServices producerServices;
	
	@Autowired
	private UserService userServices;
	
	@Autowired
	private StandardUserServices stdUserServices;
	
	@Autowired
	private VehicleServices vehicleServices;
	
	@Autowired
	private RoomServices roomServices;
	
	@Autowired
	private TicketServices ticketServices;
	
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorityServices authorityServices;
	
	@Autowired
	private ImageServices imageServices;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@GetMapping(path="/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<AirLine>> searchAirline(Pageable pageable, @RequestParam String name) {
		return new ResponseEntity<Page<AirLine>>(airLineServices.findByName('%' + name + '%', pageable), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,path="/addAirline", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<Page<AirLine>> addAirline(Pageable pageable, @RequestParam("image") MultipartFile image,
													@RequestParam("model") String a )  {
		AirLine airline = new AirLine();
		try {
			airline = mapper.readValue(a, AirLine.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(airLineServices.findOneByAirLineName(airline.getAirLineName()) != null)	{
			return new ResponseEntity<Page<AirLine>>(HttpStatus.BAD_REQUEST);
		}
		AirLine air = airLineServices.save(airline);
		String imgPath = imageServices.saveAirlineImg(image, air.getAirLineID());
		if(imgPath.equals("")) {
			airLineServices.delete(air.getAirLineID());
			return new ResponseEntity<Page<AirLine>>(HttpStatus.BAD_REQUEST);
		}
		air.setImage(imgPath);
		airLineServices.save(air);
		return new ResponseEntity<Page<AirLine>>(airLineServices.findAll(pageable),HttpStatus.OK);
	}
	
	@DeleteMapping(path="/deleteAirline/{id}")
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<Page<AirLine>> deleteAirline(Pageable pageable, @PathVariable Integer id)
	{
		airLineServices.delete(id);
		return new ResponseEntity<Page<AirLine>>(airLineServices.findAll(pageable),HttpStatus.OK);
	}
	
	@GetMapping(path="/showAirLines", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<AirLine>> showAirLines(Pageable pageable)  {
		return new ResponseEntity<Page<AirLine>>(airLineServices.findAll(pageable),HttpStatus.OK);
	}
	
	@GetMapping(path="/getAirline/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AirLine> getAirline(@PathVariable Integer id)
	{
		return new ResponseEntity<AirLine>(airLineServices.findOne(id),HttpStatus.OK);
	}
	
	@PutMapping(path="/editAirline/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<AirLine> editAirline(@PathVariable Integer id, @RequestBody AirLine modifiedAirline)
	{
		modifiedAirline.setAirLineID(id);
		AirLine air = airLineServices.findOne(id);
		modifiedAirline.setAirlineEarning(air.getAirlineEarning());
		return new ResponseEntity<AirLine>(airLineServices.save(modifiedAirline), HttpStatus.OK);
	}
	

	@GetMapping(path="/{airlineId}/showDestinations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Destination>> getDestinations(Pageable pageable, @PathVariable Integer airlineId) {
		return new ResponseEntity<Page<Destination>>(destinationServices.findAll(airlineId, pageable),HttpStatus.OK);
	}
	
	@GetMapping(path="/showAllDestinations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Destination>> getAllDestinations() {
		return new ResponseEntity<List<Destination>>(destinationServices.findAll(),HttpStatus.OK);
	}
	
	@PostMapping(path="/{airlineId}/addDestination", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('AIRLINE_ADMIN')")
	public ResponseEntity<Page<Destination>> addDestination (Pageable pageable, @PathVariable Integer airlineId, @RequestBody Destination destination) {
		destinationServices.save(airlineId, destination);
		return new ResponseEntity<Page<Destination>> (destinationServices.findAll(airlineId, pageable), HttpStatus.OK);
	}
	
	@GetMapping(path="/{airlineId}/getDestination/{destinationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('AIRLINE_ADMIN')")
	public ResponseEntity<Destination> getDestination(@PathVariable Integer airlineId, @PathVariable Integer destinationId)
	{
		return new ResponseEntity<Destination>(destinationServices.findOne(airlineId, destinationId), HttpStatus.OK);
	}
	
	@PutMapping(path="/{airlineId}/editDestination/{destinationId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('AIRLINE_ADMIN')")
	public ResponseEntity<Destination> editDestination(@PathVariable Integer airlineId, @PathVariable Integer destinationId, @RequestBody Destination modifiedDestination)
	{
		modifiedDestination.setIdDestination(destinationId);
		destinationServices.update(airlineId, modifiedDestination);
		
		return new ResponseEntity<Destination>(modifiedDestination, HttpStatus.OK);
	}
	
	@DeleteMapping(path="{airlineId}/deleteDestination/{destinationId}")
	@PreAuthorize("hasRole('AIRLINE_ADMIN')")
	public ResponseEntity<Page<Destination>> deleteDestination(Pageable pageable, @PathVariable Integer airlineId, @PathVariable Integer destinationId)
	{
		destinationServices.delete(airlineId, destinationId);
		return new ResponseEntity<Page<Destination>>(destinationServices.findAll(airlineId, pageable),HttpStatus.OK);
	}
	
	@GetMapping(path="/searchFlights", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<FlightDTO>> searchFlights(Pageable pageable, @RequestParam String type, @RequestParam String flightClass, 
			@RequestParam String from, @RequestParam String to, @RequestParam Long takeOff, @RequestParam Long landing, @RequestParam String numberOfPeople,
			@RequestParam String airLineName, @RequestParam String flightLength, @RequestParam String priceFrom, @RequestParam String priceTo) throws ParseException {
		return new ResponseEntity<Set<FlightDTO>>(flightServices.search(type, flightClass, from, to, takeOff, landing, numberOfPeople, airLineName, flightLength, priceFrom, priceTo, pageable), HttpStatus.OK);
	}
	
	@GetMapping(path="/{airlineId}/showFlights", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<FlightDTO>> getFlights(Pageable pageable, @PathVariable Integer airlineId) {
		return new ResponseEntity<Page<FlightDTO>>(flightServices.findAllAndConvert(airlineId, pageable),HttpStatus.OK);
	}
	
	@GetMapping(path="/{airlineId}/showAllFlights", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<FlightDTO>> getAllFlights(@PathVariable Integer airlineId) {
		return new ResponseEntity<Set<FlightDTO>>(flightServices.findAllAndConvert(airlineId), HttpStatus.OK);
	}
	
	@GetMapping(path="/showAllFlights", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<FlightDTO>> getAllPagedFlights(Pageable pageable) {
		return new ResponseEntity<Page<FlightDTO>>(flightServices.findAllAndConvert(pageable),HttpStatus.OK);
	}
	
	@PostMapping(path="/{airlineId}/addFlight", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('AIRLINE_ADMIN')")
	public ResponseEntity<Page<FlightDTO>> addFlight (Pageable pageable, @PathVariable Integer airlineId, @RequestBody FlightDTO f) throws ParseException {
		System.out.println(f);
		Flight fl = f.convert(new HashSet<Destination>(destinationServices.findAll()), airplaneServices.findAll());
		System.out.println(fl);
		flightServices.save(airlineId, fl);
		return new ResponseEntity<Page<FlightDTO>> (flightServices.findAllAndConvert(airlineId, pageable), HttpStatus.OK);
	}
	
	@GetMapping(path="/{airlineId}/getFlight/{flightId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FlightDTO> getFlight(@PathVariable Integer airlineId, @PathVariable Integer flightId)
	{
		return new ResponseEntity<FlightDTO>(flightServices.findOneAndConvert(airlineId, flightId), HttpStatus.OK);
	}
	
	@GetMapping(path="/{airlineId}/addStopDestination/{flightId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Destination>> addStopDestination (Pageable pageable, @PathVariable Integer flightId, @RequestBody Destination destination) {
		destinationServices.save(flightId, destination);
		return new ResponseEntity<Page<Destination>> (destinationServices.findAll(flightId, pageable), HttpStatus.OK);
	}
	
	@PutMapping(path="/{airlineId}/editFlight/{flightId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('AIRLINE_ADMIN')")
	public ResponseEntity<Flight> editFlight(@PathVariable Integer airlineId, @PathVariable Integer flightId, @RequestBody FlightDTO modifiedFlight) throws ParseException
	{
		Flight f = modifiedFlight.convert(destinationServices.findAll(airlineId), airplaneServices.findAll());
		f.setIdFlight(flightId);
		f.setAirLine(airLineServices.findOne(airlineId));
		flightServices.update(airlineId, f);
		
		return new ResponseEntity<Flight>(f, HttpStatus.OK);
	}
	
	@DeleteMapping(path="{airlineId}/deleteFlight/{flightId}")
	@PreAuthorize("hasRole('AIRLINE_ADMIN')")
	public ResponseEntity<Page<FlightDTO>> deleteFlight(Pageable pageable, @PathVariable Integer airlineId, @PathVariable Integer flightId)
	{
		flightServices.delete(airlineId, flightId);
		return new ResponseEntity<Page<FlightDTO>>(flightServices.findAllAndConvert(airlineId, pageable),HttpStatus.OK);
	}
	
	@PostMapping(path="/{airlineId}/buyticket/{flightId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<Integer> buyTicket(@PathVariable Integer airlineId, @PathVariable Integer flightId, HttpServletRequest request, @RequestBody ReservationUserDTO user) throws UnknownHostException
	{
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User loggedUser = this.userServices.findByUsername(username);

		switch(user.getUserType()) {
		case CURRENT:		    
			return new ResponseEntity<Integer>(ticketServices.addTicket(airlineId, flightId, user, loggedUser), HttpStatus.OK);
		case UNREGISTERED:
			User registered = userServices.findByEmail(user.getEmail());
			if (registered == null) {
				return new ResponseEntity<Integer>(ticketServices.addTicket(airlineId, flightId, user), HttpStatus.OK);
			} else {
				return new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
			}
		case REGISTERED:
			StandardUser standardLoggedUser = stdUserServices.findByUsername(username);
			FriendsListsDTO friends = new FriendsListsDTO(standardLoggedUser);
			User registeredUser = userServices.findByEmail(user.getEmail());
			for (FriendsDTO f: friends.getAccepted()) {
				if (f.getOther().getEmail().equals(user.getEmail())) {
					return new ResponseEntity<Integer>(ticketServices.addTicket(airlineId, flightId, user, registeredUser), HttpStatus.OK);
				}
			}
		default:
			return new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path="/{airlineId}/cancelReservation/{flightId}")
	public ResponseEntity<Integer> cancelReservation(@PathVariable Integer airlineId, @PathVariable Integer flightId, @RequestBody List<Integer> ids){
		for(Integer id: ids) {
			this.ticketServices.deleteTicket(flightId, id);
		}
		return new ResponseEntity<Integer>(-1, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path="/{airlineId}/continueReservation/{flightId}")
	public ResponseEntity<Integer> continueReservation(@PathVariable Integer airlineId, @PathVariable Integer flightId, HttpServletRequest request, @RequestBody List<Integer> ids){
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User loggedUser = this.userServices.findByUsername(username);

		for(Integer id: ids) {
			Ticket t = this.ticketServices.findOne(id);
			if (!t.getEmail().equals(loggedUser.getEmail())) {
				String text = "You have a new invitation for a trip with your friend " + 
						loggedUser.getFirstName() + " " + loggedUser.getLastName() + " on Reservify platform. Please follow this link to proceed: http://localhost:8888/invitation/flight.html?ticketId=" + t.getIdTicket();
				this.producerServices.sendEmailTo("Invitation for trip on Reservify!", t.getEmail(), text);
			}
		}
		return new ResponseEntity<Integer>(1, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path="/finishReservation")
	public ResponseEntity<Integer> finishReservation(@RequestBody ReservationIdsDTO ids, HttpServletRequest request) {
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User loggedUser = this.userServices.findByUsername(username);
		
		Ticket t = this.ticketServices.findOne(ids.getTicketId());
		String text = "Your reservation is successful on Reservify platform. You reserved flight from " 
				+ t.getFlight().getStartDestination().getDestinationName() + " to " + t.getFlight().getFinalDestination().getDestinationName() + ".";
		
		if (ids.getRoomIds().length > 0 || ids.getVehicleIds().length > 0) {
			text += "Also, you made some additional reservations: ";
			for(Integer id: ids.getRoomIds()) {
				Room r = roomServices.findOne(id);
				text += "Room in " + r.getHotel().getHotelName() + " hotel, room capacity: " + r.getRoomCapacity() + ". ";
			}
			
			for(Integer id: ids.getVehicleIds()) {
				Vehicle v = vehicleServices.findOne(id);
				text += "Vehicle " + v.getVehicleName() + " from " + v.getBranch().getService().getRentACarName() + " rent-a-car service.";
				
			}
		}
		
		this.producerServices.sendEmailTo("Successful reservation on Reservify!", loggedUser.getEmail(), text);
		return new ResponseEntity<Integer>(1, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path="/getTicket/{ticketId}")
	public ResponseEntity<TicketDTO> getTicket(@PathVariable Integer ticketId) {
		return new ResponseEntity<TicketDTO>(this.ticketServices.findOne(ticketId).convert(), HttpStatus.OK);
	}
	
	@PostMapping(path="/reservationResponse/{ticketId}")
	public void respond(@PathVariable Integer ticketId, @RequestBody InvitationResponseType type, HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User loggedUser = this.userServices.findByUsername(username);
		switch(type) {
		case ACCEPTED:
			ticketServices.changeTicketStatus(ticketId, loggedUser.getId());
			break;
		case DECLINED:
			ticketServices.deleteTicket(this.ticketServices.findOne(ticketId).getFlight().getIdFlight(), ticketId);
			break;
		}
	}
	
	
	@GetMapping(path="/showUser/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<ArrayList<UserDTO>> getUsers(@PathVariable Integer id){
		ArrayList<UserDTO> users = new ArrayList<UserDTO>();
		for(User us : airLineServices.findOne(id).getAdmins()) {
			users.add(new UserDTO(us, new TokenState()));
		}
		return new ResponseEntity<ArrayList<UserDTO>>(users,HttpStatus.OK);
	}
	
	@PostMapping(path="/addUser/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<Boolean> addUser(@PathVariable Integer id, @RequestParam("image") MultipartFile image,
											@RequestParam("model") String a ){
		UserDTO user;
		try {
			user = mapper.readValue(a, UserDTO.class);
		} catch (IOException e) {
			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}
		AirLine airline = airLineServices.findOne(id);
		AirLineAdmin admin = new AirLineAdmin();
		admin.setPassword(passwordEncoder.encode("123"));
		admin.setUsername(user.getUsername());
		admin.setFirstName(user.getFirstName());
		admin.setLastName(user.getLastName());
		admin.setEmail(user.getEmail());
		admin.setEnabled(true);
		admin.setConfirmed(false);
		admin.setAirLine(airline);
		airline.getAdmins().add(admin);
		admin.setAuthorities(Arrays.asList(authorityServices.findOne(3)));
		admin.setImage(imageServices.getUserPath(image, admin.getUsername()));
		
		airline = airLineServices.save(airline);
		
		imageServices.saveUserImg(image, admin.getUsername());
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	
	@PostMapping(path = "/makeFastTicket/{flightId}")
	@PreAuthorize("hasRole('AIRLINE_ADMIN')")
	@Transactional
	public ResponseEntity<Void> makeFastTicket(@PathVariable Integer flightId, @RequestBody TicketDTO ticket){
		Flight flight = flightServices.lockFlight(flightId);
		Ticket t = new Ticket(Double.parseDouble(ticket.getTicketPrice()), ticket.getSeat(), "",
							"", "", TicketStatus.FAST, flight, "", flight.getAirLine());
		ticketServices.save(t);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping(path = "/reserveFastTicket/{ticketId}")
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public ResponseEntity<Void> takeFastTicket(@PathVariable Integer ticketId, @RequestBody ReservationUserDTO res, HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		StandardUser loggedUser = stdUserServices.findByUsername(username);
		
		Ticket ticket = ticketServices.lockTicket(ticketId);
		
		if(ticket.getStatus().getValue() != 3) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		ticket.setEmail(loggedUser.getEmail());
		ticket.setFirstName(loggedUser.getFirstName());
		ticket.setLastName(loggedUser.getLastName());
		ticket.setPassport(res.getPassport());
		ticket.setStatus(TicketStatus.ACCEPTED);
		ticket.setUser(loggedUser);
		ticketServices.save(ticket);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping(path = "/allFastTickets/{airlineId}")
	@PreAuthorize("hasRole('USER') or hasRole('AIRLINE_ADMIN')")
	public ResponseEntity<Page<TicketDTO>> getAllFastTicket(@PathVariable Integer airlineId,Pageable pageable)	{
		Page<Ticket> tickets = ticketServices.findAllByStatus(airlineId,TicketStatus.FAST, pageable);
		List<TicketDTO> ticketsDTO = new ArrayList<TicketDTO>();
		for(Ticket t : tickets.getContent()) {
			TicketDTO tic = t.convert();
			tic.setFlight(t.getFlight().convert());
			ticketsDTO.add(tic);
		}
		return new ResponseEntity<Page<TicketDTO>>(new PageImpl<TicketDTO>(ticketsDTO),
												HttpStatus.OK);
	}
	
	@Scheduled(fixedDelay = 3600000)
	public void cancelReservations() throws InterruptedException {
		List<Ticket> l = ticketServices.findAll();
		for(Ticket t: l) {
			if (t.getStatus().equals(TicketStatus.PENDING)) {
				long difference = new Date().getTime() - t.getCreated().getTime();
				long differenceDaysSinceReservation = difference / (1000 * 60 * 60 * 24);
				long difference2 = t.getFlight().getTakeOffDate().getTime() - new Date().getTime();
				long differenceTillDepartureInHours = difference2 / (60 * 60 * 1000);
				if (differenceDaysSinceReservation >= 3 || differenceTillDepartureInHours <= 3) {
					ticketServices.deleteTicket(t.getFlight().getIdFlight(), t.getIdTicket());
				}
			}
		}
		
	}
	
	
	
}
