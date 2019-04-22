package siit.tim25.rezervisi.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import siit.tim25.rezervisi.Beans.Hotel;
import siit.tim25.rezervisi.Beans.Room;
import siit.tim25.rezervisi.Beans.users.HotelAdmin;
import siit.tim25.rezervisi.DTO.UserDTO;
import siit.tim25.rezervisi.Services.HotelServices;
import siit.tim25.rezervisi.Services.RoomServices;
import siit.tim25.rezervisi.Services.users.AuthorityServices;
import siit.tim25.rezervisi.security.model.TokenState;
import siit.tim25.rezervisi.security.model.User;

@RestController
@RequestMapping(path="app/hotels")
public class HotelController {
	

	@Autowired
	private HotelServices hotelServices;
	
	@Autowired
	private RoomServices roomServices;
	
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorityServices authorityServices;
	
	@PostMapping(path="/addHotel", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<List<Hotel>> addHotel(@RequestBody Hotel hotel)  {	
		hotelServices.save(hotel);
		return new ResponseEntity<List<Hotel>>(hotelServices.findAll(),HttpStatus.OK);
	}
	
	@DeleteMapping(path="/deleteHotel/{id}")
	public ResponseEntity<List<Hotel>> deleteHotel(@PathVariable Integer id)
	{
		hotelServices.delete(id);
		return new ResponseEntity<List<Hotel>>(hotelServices.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(path="/showHotels", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Hotel>> showHotels()
	{
		return new ResponseEntity<List<Hotel>>(hotelServices.findAll(),HttpStatus.OK);
		
	}
	
	@GetMapping(path="/getHotel/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> getHotel(@PathVariable Integer id)
	{
		return new ResponseEntity<Hotel>(hotelServices.findOne(id),HttpStatus.OK);
	}
	
	@PutMapping(path="/editHotel/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> editHotel(@PathVariable Integer id, @RequestBody Hotel modifiedHotel)
	{
		modifiedHotel.setHotelID(id);
		Hotel h = hotelServices.findOne(id);
		modifiedHotel.setHotelAverageGrade(h.getHotelAverageGrade());
		modifiedHotel.setHotelEarning(h.getHotelEarning());
		return new ResponseEntity<Hotel>(hotelServices.save(modifiedHotel), HttpStatus.OK);
	}

	
	@GetMapping(path="/{hotelId}/showRooms", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Room>> getRooms(@PathVariable Integer hotelId) {
		return new ResponseEntity<List<Room>>(roomServices.findAll(hotelId),HttpStatus.OK);
	}
	
	@PostMapping(path="/{hotelId}/addRoom", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('HOTEL_ADMIN')")
	public ResponseEntity<List<Room>> addRoom (@PathVariable Integer hotelId, @RequestBody Room r) throws ParseException {
		roomServices.save(hotelId, r);
		return new ResponseEntity<List<Room>> (roomServices.findAll(hotelId), HttpStatus.OK);
	}
	
	@GetMapping(path="/{hotelId}/getRoom/{roomId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('HOTEL_ADMIN')")
	public ResponseEntity<Room> getRoom(@PathVariable Integer hotelId, @PathVariable Integer roomId)
	{
		return new ResponseEntity<Room>(roomServices.findOne(hotelId, roomId), HttpStatus.OK);
	}
	
	@PutMapping(path="/{hotelId}/editRoom/{roomId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('HOTEL_ADMIN')")
	public ResponseEntity<Room> editRoom(@PathVariable Integer hotelId, @PathVariable Integer roomId, @RequestBody Room modifiedRoom) throws ParseException
	{
		modifiedRoom.setRoomID(roomId);
		roomServices.update(hotelId, modifiedRoom);
		
		return new ResponseEntity<Room>(modifiedRoom, HttpStatus.OK);
	}
	
	@DeleteMapping(path="{hotelId}/deleteRoom/{roomId}")
	@PreAuthorize("hasRole('HOTEL_ADMIN')")
	public ResponseEntity<List<Room>> deleteRoom(@PathVariable Integer hotelId, @PathVariable Integer roomId)
	{
		roomServices.delete(hotelId, roomId);
		return new ResponseEntity<List<Room>>(roomServices.findAll(hotelId),HttpStatus.OK);
	}
	
	@GetMapping(path="/showUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<ArrayList<UserDTO>> getUsers(@PathVariable Integer id){
		ArrayList<UserDTO> users = new ArrayList<UserDTO>();
		for(User us : hotelServices.findOne(id).getAdmins()) {
			users.add(new UserDTO(us, new TokenState()));
		}
		return new ResponseEntity<ArrayList<UserDTO>>(users,HttpStatus.OK);
	}
	
	@PostMapping(path="/addUser/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<Boolean> addUser(@PathVariable Integer id, @RequestBody UserDTO user){
		
		Hotel hotel = hotelServices.findOne(id);
		HotelAdmin admin = new HotelAdmin();
		admin.setPassword(passwordEncoder.encode("123"));
		admin.setUsername(user.getUsername());
		admin.setFirstName(user.getFirstName());
		admin.setLastName(user.getLastName());
		admin.setEmail(user.getEmail());
		admin.setEnabled(true);
		admin.setConfirmed(false);
		admin.setHotel(hotel);
		hotel.getAdmins().add(admin);
		admin.setAuthorities(Arrays.asList(authorityServices.findOne(2)));
		hotelServices.save(hotel);
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
}

