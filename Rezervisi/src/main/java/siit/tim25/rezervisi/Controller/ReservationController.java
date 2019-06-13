package siit.tim25.rezervisi.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.Beans.RentACar;
import siit.tim25.rezervisi.DTO.FlightDTO;
import siit.tim25.rezervisi.DTO.HotelDTO;
import siit.tim25.rezervisi.DTO.RoomDTO;
import siit.tim25.rezervisi.DTO.RoomReservationDTO;
import siit.tim25.rezervisi.DTO.TicketDTO;
import siit.tim25.rezervisi.DTO.VehicleDTO;
import siit.tim25.rezervisi.DTO.VehicleReservationDTO;
import siit.tim25.rezervisi.Services.AirLineServices;
import siit.tim25.rezervisi.Services.FlightServices;
import siit.tim25.rezervisi.Services.HotelServices;
import siit.tim25.rezervisi.Services.RentACarServices;
import siit.tim25.rezervisi.Services.RoomReservationServices;
import siit.tim25.rezervisi.Services.RoomServices;
import siit.tim25.rezervisi.Services.TicketServices;
import siit.tim25.rezervisi.Services.VehicleReservationServices;
import siit.tim25.rezervisi.Services.VehicleServices;
import siit.tim25.rezervisi.security.TokenUtils;
import siit.tim25.rezervisi.security.model.User;
import siit.tim25.rezervisi.security.servicce.UserService;

@RestController
@RequestMapping(path="app/reservations")
public class ReservationController {
	
	@Autowired
	private TicketServices ticketServices;
	
	@Autowired
	private RoomReservationServices roomReservationServices;
	
	@Autowired
	private VehicleReservationServices vehicleReservationServices;
	
	@Autowired
	private VehicleServices vehicleServices;

	@Autowired
	private RentACarServices rentACarServices;
	
	@Autowired
	private RoomServices roomServices;
	
	@Autowired
	private HotelServices hotelServices;
	
	@Autowired
	private FlightServices flightServices;
	
	@Autowired
	private AirLineServices airLineServices;
	
	@Autowired
	private UserService userServices;

	@Autowired
	private TokenUtils tokenUtils;
	
	@GetMapping(path="/{userId}/getTickets", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TicketDTO>> getTicketsByUser(@PathVariable Integer userId) {
		return new ResponseEntity<List<TicketDTO>>(ticketServices.findTicketsByUserId(userId), HttpStatus.OK);
	}
	
	@GetMapping(path="/{userId}/getRoomReservations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RoomReservationDTO>> getRoomReservationsByUser(@PathVariable Integer userId) {
		return new ResponseEntity<List<RoomReservationDTO>>(roomReservationServices.findRoomReservationsByUserId(userId), HttpStatus.OK);

	}
	
	@GetMapping(path="/{userId}/getVehicleReservations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VehicleReservationDTO>> getVehicleReservationsByUser(@PathVariable Integer userId) {
		return new ResponseEntity<List<VehicleReservationDTO>>(vehicleReservationServices.findVehicleReservationsByUserId(userId), HttpStatus.OK);

	}
		
	

	@GetMapping(path="/getPastVehicleReservations", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Page<VehicleDTO>> findPastVehicleReservations(HttpServletRequest request, Pageable pageable)
	{
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User loggedUser = this.userServices.findByUsername(username);
		
		return new ResponseEntity<Page<VehicleDTO>>(vehicleServices.findPastVehicleReservations(loggedUser.getId(), pageable), HttpStatus.OK);
	}
	
	@GetMapping(path="/getPastRentACarReservations", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Page<RentACar>> findPastRentACarReservations(HttpServletRequest request, Pageable pageable)
	{
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User loggedUser = this.userServices.findByUsername(username);
		
		return new ResponseEntity<Page<RentACar>>(rentACarServices.findPastRentACarReservations(loggedUser.getId(), pageable), HttpStatus.OK);
	}
	
	@GetMapping(path="/getPastRoomReservations", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Page<RoomDTO>> findPastRoomReservations(HttpServletRequest request, Pageable pageable)
	{
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User loggedUser = this.userServices.findByUsername(username);
		
		return new ResponseEntity<Page<RoomDTO>>(roomServices.findPastRoomReservations(loggedUser.getId(), pageable), HttpStatus.OK);
	}
	
	@GetMapping(path="/getPastHotelReservations", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Page<HotelDTO>> findPastHotelReservations(HttpServletRequest request, Pageable pageable)
	{
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User loggedUser = this.userServices.findByUsername(username);
		
		return new ResponseEntity<Page<HotelDTO>>(hotelServices.findPastHotelReservations(loggedUser.getId(), pageable), HttpStatus.OK);
	}
	
	@GetMapping(path="/getPastFlightReservations", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Page<FlightDTO>> findPastFlightReservations(HttpServletRequest request, Pageable pageable)
	{
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User loggedUser = this.userServices.findByUsername(username);
		
		return new ResponseEntity<Page<FlightDTO>>(flightServices.findPastFlightReservations(loggedUser.getId(), pageable), HttpStatus.OK);
	}
	
	@GetMapping(path="/getPastAirLineReservations", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Page<AirLine>> findPastAirLineReservations(HttpServletRequest request, Pageable pageable)
	{
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User loggedUser = this.userServices.findByUsername(username);
		
		return new ResponseEntity<Page<AirLine>>(airLineServices.findPastAirLineReservations(loggedUser.getId(), pageable), HttpStatus.OK);
	}
}
