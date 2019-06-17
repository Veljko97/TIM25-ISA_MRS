package siit.tim25.rezervisi.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.Beans.Flight;
import siit.tim25.rezervisi.Beans.Hotel;
import siit.tim25.rezervisi.Beans.RentACar;
import siit.tim25.rezervisi.Beans.Room;
import siit.tim25.rezervisi.Beans.RoomReservation;
import siit.tim25.rezervisi.Beans.Vehicle;
import siit.tim25.rezervisi.Beans.VehicleReservation;
import siit.tim25.rezervisi.Beans.Grades.AirLineGrade;
import siit.tim25.rezervisi.Beans.Grades.FlightGrade;
import siit.tim25.rezervisi.Beans.Grades.HotelGrade;
import siit.tim25.rezervisi.Beans.Grades.RentACarGrade;
import siit.tim25.rezervisi.Beans.Grades.RoomGrade;
import siit.tim25.rezervisi.Beans.Grades.VehicleGrade;
import siit.tim25.rezervisi.Beans.users.Friends;
import siit.tim25.rezervisi.Beans.users.StandardUser;
import siit.tim25.rezervisi.DTO.FriendsDTO;
import siit.tim25.rezervisi.DTO.FriendsListsDTO;
import siit.tim25.rezervisi.DTO.StandardUserDTO;
import siit.tim25.rezervisi.Services.AirLineServices;
import siit.tim25.rezervisi.Services.FlightServices;
import siit.tim25.rezervisi.Services.HotelServices;
import siit.tim25.rezervisi.Services.ProducerServices;
import siit.tim25.rezervisi.Services.RentACarServices;
import siit.tim25.rezervisi.Services.RoomServices;
import siit.tim25.rezervisi.Services.VehicleServices;
import siit.tim25.rezervisi.Services.users.FriendsServices;
import siit.tim25.rezervisi.Services.users.GradingServices;
import siit.tim25.rezervisi.Services.users.StandardUserServices;
import siit.tim25.rezervisi.security.TokenUtils;
import siit.tim25.rezervisi.security.model.TokenState;

@RestController
@RequestMapping(path="/app/users")
public class StandardUserController {

	@Autowired
	private ProducerServices producer;
	
	@Autowired
	private StandardUserServices userServices;
	
	@Autowired
	private FriendsServices friendsServices;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private AirLineServices airLineServices;
	
	@Autowired
	private HotelServices hotelServices;
	
	@Autowired
	private RentACarServices rentACarServices;
	
	@Autowired
	private RoomServices roomServices;
	
	@Autowired
	private VehicleServices vehicleServices;
	
	@Autowired
	private FlightServices flightServices;
	
	@Autowired
	private GradingServices gradingServices;
	
	@RequestMapping(method = RequestMethod.POST, path = "/sendRequest/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	private ResponseEntity<Void> sendRequest(@PathVariable Integer userId, HttpServletRequest request) {
		StandardUser reciver = userServices.findOne(userId);
		if(reciver == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
		String token = tokenUtils.getToken(request);
		StandardUser sending = userServices.findByUsername(tokenUtils.getUsernameFromToken(token));
		if(sending.getId().equals(userId)) {
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
		Friends fRequest = friendsServices.findFriends(userId, sending.getId());
		if(fRequest != null) {
			if(fRequest.getSender().getId().equals(userId)) {
				fRequest.setConfirmed(true);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}else {
			fRequest = new Friends(sending, reciver);
		}
		Friends fr = friendsServices.save(fRequest);
		
		producer.sendRequestTo(userId.toString(), new FriendsDTO(fr.getId(), fr.getSender()));
		producer.sendFriendRequestTo(reciver.getUsername(), sending.getUsername());
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path = "/myFriends", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<FriendsListsDTO> findFrends(HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		StandardUser user = userServices.findByUsername(tokenUtils.getUsernameFromToken(token));
		return new ResponseEntity<FriendsListsDTO>(new FriendsListsDTO(user), HttpStatus.OK);
	}
	
	@PutMapping(path = "/confirmeRequest/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<FriendsDTO> confirme(@PathVariable Integer requestId, HttpServletRequest request){
		Friends fr = friendsServices.findOne(requestId);
		String token = tokenUtils.getToken(request);
		StandardUser user = userServices.findByUsername(tokenUtils.getUsernameFromToken(token));
		if(!user.getId().equals(fr.getReceiver().getId())) {
			return new ResponseEntity<FriendsDTO>(HttpStatus.NOT_ACCEPTABLE);
		}
		fr.setConfirmed(true);
		friendsServices.save(fr);
		producer.sendResponseTo(fr.getSender().getId().toString(), new FriendsDTO(fr.getId(), user), true);
		return new ResponseEntity<FriendsDTO>(new FriendsDTO(requestId, fr.getSender()),HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/removeFriend/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Integer> refuse(@PathVariable Integer requestId, HttpServletRequest request){
		Friends fr = friendsServices.findOne(requestId);
		String token = tokenUtils.getToken(request);
		StandardUser user = userServices.findByUsername(tokenUtils.getUsernameFromToken(token));
		if(!user.getId().equals(fr.getReceiver().getId())) {
			return new ResponseEntity<Integer>(HttpStatus.NOT_ACCEPTABLE);
		}
		StandardUser sender = fr.getSender();
		FriendsDTO friendDTO = new FriendsDTO(requestId, fr.getSender());
		fr.setReceiver(null);
		fr.setSender(null);
		friendsServices.delete(fr);
		producer.sendResponseTo(sender.getId().toString(), friendDTO, false);
		return new ResponseEntity<Integer>(requestId, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/deleteFriend/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Integer> delete(@PathVariable Integer requestId, HttpServletRequest request){
		Friends fr = friendsServices.findOne(requestId);
		String token = tokenUtils.getToken(request);
		StandardUser user = userServices.findByUsername(tokenUtils.getUsernameFromToken(token));
		StandardUser other = null;
		if(user.getId().equals(fr.getReceiver().getId())){
			other = fr.getSender();
		}
		if(user.getId().equals(fr.getSender().getId())) {
			other = fr.getReceiver();
		}
		if(other == null) {
			return new ResponseEntity<Integer>(HttpStatus.NOT_ACCEPTABLE);
		}
		FriendsDTO friendDTO = new FriendsDTO(requestId, user);
		fr.setReceiver(null);
		fr.setSender(null);
		friendsServices.delete(fr);
		producer.sendDeleteTo(other.getId().toString(), friendDTO);
		return new ResponseEntity<Integer>(requestId, HttpStatus.OK);
	}
	
	@GetMapping(path = "/findUser", params = "serchParam", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<StandardUserDTO>> findUsers(@RequestParam("serchParam") String serchParam, Pageable pageable, HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		Integer myId;
		if(token != null) {
			StandardUser user = userServices.findByUsername(tokenUtils.getUsernameFromToken(token));
			myId = user.getId();
		}else {
			myId = -1;
		}
		
		Page<StandardUser> page = userServices.findMatch(serchParam, myId, pageable);
		List<StandardUserDTO> listDTO = new ArrayList<StandardUserDTO>();
		for(StandardUser userr : page.getContent()) {
			listDTO.add(new StandardUserDTO(userr, new TokenState()));
		}
		return new ResponseEntity<Page<StandardUserDTO>>(new PageImpl<StandardUserDTO>(listDTO,pageable ,page.getTotalElements()), HttpStatus.OK);
	}
	
	@PostMapping(path="/gradeAirLine/{id}")
	public ResponseEntity<Void> gradeAirLine(@PathVariable Integer id, @RequestBody Double score, HttpServletRequest request){
		if(score < 0 || score >5) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		String token = tokenUtils.getToken(request);
		StandardUser user = userServices.findByUsername(tokenUtils.getUsernameFromToken(token));
		AirLine air = airLineServices.findOne(id);
		AirLineGrade grade = null;
		for(AirLineGrade airline : user.getAirLineGrades()) {
			if(airline.getAirLine().getAirLineID().equals(id)) {
				grade = airline;
				grade.setScore(score);
				break;
			}
		}		
		if(grade == null) {
			grade = new AirLineGrade(null, score, air, user);
			air.getGrades().add(grade);
		}
		
		gradingServices.updateGrade(air, "AirLine");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(path="/gradeHotel/{id}")
	public ResponseEntity<Void> gradeHotel(@PathVariable Integer id, @RequestBody Double score, HttpServletRequest request){
		if(score < 0 || score >5) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		String token = tokenUtils.getToken(request);
		StandardUser user = userServices.findByUsername(tokenUtils.getUsernameFromToken(token));
		Hotel hotel = hotelServices.findOne(id);
		HotelGrade grade = null;
		for(HotelGrade ht : user.getHotelGrades()) {
			if(ht.getHotel().getHotelID().equals(id)) {
				grade = ht;
				grade.setScore(score);
				break;
			}
		}		
		if(grade == null) {
			grade = new HotelGrade(null, score, hotel, user);
			hotel.getGrades().add(grade);
		}
		
		gradingServices.updateGrade(hotel, "Hotel");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(path="/gradeRentACar/{id}")
	public ResponseEntity<Void> gradeRentACar(@PathVariable Integer id, @RequestBody Double score, HttpServletRequest request){
		if(score < 0 || score >5) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		String token = tokenUtils.getToken(request);
		StandardUser user = userServices.findByUsername(tokenUtils.getUsernameFromToken(token));
		RentACar rent = rentACarServices.findOne(id);
		RentACarGrade grade = null;
		for(RentACarGrade ra : user.getRentACarGrades()) {
			if(ra.getRentACar().getRentACarID().equals(id)) {
				grade = ra;
				grade.setScore(score);
				break;
			}
		}		
		if(grade == null) {
			grade = new RentACarGrade(null, score, rent, user);
			rent.getGrades().add(grade);
		}
		
		gradingServices.updateGrade(rent, "RentACar");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(path="/gradeRoom/{id}")
	public ResponseEntity<Void> gradeRoom(@PathVariable Integer id, @RequestBody Double score, HttpServletRequest request){
		if(score < 0 || score >5) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		String token = tokenUtils.getToken(request);
		StandardUser user = userServices.findByUsername(tokenUtils.getUsernameFromToken(token));
		for(RoomReservation res : user.getRoomReservation()) {
			if(res.getRoom().getRoomID().equals(id)) {
				if(res.getReservationEnd().after(new Date())) {
					return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
				}
				break;
			}
		}
		Room room = roomServices.findOne(id);
		RoomGrade grade = null;
		for(RoomGrade ro : user.getRoomGrades()) {
			if(ro.getRoom().getRoomID().equals(id)) {
				grade = ro;
				grade.setScore(score);
				break;
			}
		}		
		if(grade == null) {
			grade = new RoomGrade(null, score, room, user);
			room.getGrades().add(grade);
		}
		
		gradingServices.updateGrade(room, "Room");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(path="/gradeFlight/{id}")
	public ResponseEntity<Void> gradeFlight(@PathVariable Integer id, @RequestBody Double score, HttpServletRequest request){
		if(score < 0 || score >5) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		String token = tokenUtils.getToken(request);
		StandardUser user = userServices.findByUsername(tokenUtils.getUsernameFromToken(token));
		Flight flight = flightServices.findOne(id);
		if(flight.getLandingDate().after(new Date())) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		FlightGrade grade = null;
		for(FlightGrade fl : user.getFlightGrades()) {
			if(fl.getFlight().getIdFlight().equals(id)) {
				grade = fl;
				grade.setScore(score);
				break;
			}
		}		
		if(grade == null) {
			grade = new FlightGrade(null, score, flight, user);
			flight.getGrades().add(grade);
		}
		
		gradingServices.updateGrade(flight, "Flight");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(path="/gradeVehicle/{id}")
	public ResponseEntity<Void> gradeVehicle(@PathVariable Integer id, @RequestBody Double score, HttpServletRequest request){
		if(score < 0 || score >5) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		String token = tokenUtils.getToken(request);
		StandardUser user = userServices.findByUsername(tokenUtils.getUsernameFromToken(token));
		for(VehicleReservation res : user.getVehicleReservation()) {
			if(res.getVehicle().getId().equals(id)) {
				if(res.getReservationEnd().after(new Date())) {
					return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
				}
				break;
			}
		}
		Vehicle vehicle = vehicleServices.findOne(id);
		VehicleGrade grade = null;
		for(VehicleGrade vg : user.getVehicleGrades()) {
			if(vg.getVehicle().getId().equals(id)) {
				grade = vg;
				grade.setScore(score);
				break;
			}
		}		
		if(grade == null) {
			grade = new VehicleGrade(null, score, vehicle, user);
			vehicle.getGrades().add(grade);
		}
		
		gradingServices.updateGrade(vehicle, "Vehicle");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
