package siit.tim25.rezervisi.Controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import siit.tim25.rezervisi.Beans.Hotel;
import siit.tim25.rezervisi.Beans.Room;
import siit.tim25.rezervisi.Beans.users.HotelAdmin;
import siit.tim25.rezervisi.DTO.HotelDTO;
import siit.tim25.rezervisi.DTO.UserDTO;
import siit.tim25.rezervisi.Services.DestinationServices;
import siit.tim25.rezervisi.Services.HotelServices;
import siit.tim25.rezervisi.Services.ImageServices;
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
	private DestinationServices destinationServices;
	
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorityServices authorityServices;
	
	@Autowired
	private ImageServices imageServices;
	
	@Autowired
	private ObjectMapper mapper;
	
	@PostMapping(path="/addHotel", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<Page<HotelDTO>> addHotel(Pageable pageable, @RequestParam("image") MultipartFile image,
													@RequestParam("model") String a )  {
		HotelDTO hotelDTO = null;
		Hotel hotel = null;
		try {
			hotelDTO = mapper.readValue(a, HotelDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			hotel = hotelServices.save(hotelDTO.convert(destinationServices.findAll()));
		} catch (ParseException e) {
			return new ResponseEntity<Page<HotelDTO>>(HttpStatus.BAD_REQUEST);
		}
		hotel.setImage(imageServices.saveHotelImg(image, hotel.getHotelID()));
		hotelServices.save(hotel);
		return new ResponseEntity<Page<HotelDTO>>(hotelServices.findAllAndConvert(pageable),HttpStatus.OK);
	}
	
	@GetMapping(path="/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<HotelDTO>> search(Pageable pageable, @RequestParam String name, @RequestParam String destination) {
		return new ResponseEntity<Page<HotelDTO>>(hotelServices.search(name, destination,pageable), HttpStatus.OK);
	}
	
	@DeleteMapping(path="/deleteHotel/{id}")
	public ResponseEntity<Page<HotelDTO>> deleteHotel(Pageable pageable, @PathVariable Integer id)
	{
		hotelServices.delete(id);
		return new ResponseEntity<Page<HotelDTO>>(hotelServices.findAllAndConvert(pageable),HttpStatus.OK);
	}
	
	@GetMapping(path="/showHotels", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<HotelDTO>> showHotels(Pageable pageable)
	{
		return new ResponseEntity<Page<HotelDTO>>(hotelServices.findAllAndConvert(pageable),HttpStatus.OK);
		
	}
	
	@GetMapping(path="/getHotel/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HotelDTO> getHotel(@PathVariable Integer id)
	{
		return new ResponseEntity<HotelDTO>(hotelServices.findOne(id).convert(),HttpStatus.OK);
	}
	
	@PutMapping(path="/editHotel/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> editHotel(@PathVariable Integer id, @RequestBody Hotel modifiedHotel)
	{
		modifiedHotel.setHotelID(id);
		Hotel h = hotelServices.findOne(id);
		modifiedHotel.setHotelEarning(h.getHotelEarning());
		return new ResponseEntity<Hotel>(hotelServices.save(modifiedHotel), HttpStatus.OK);
	}

	
	@GetMapping(path="/{hotelId}/showRooms", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Room>> getRooms(Pageable pageable, @PathVariable Integer hotelId) {
		return new ResponseEntity<Page<Room>>(roomServices.findAll(hotelId, pageable),HttpStatus.OK);
	}

	@PostMapping(path="/{hotelId}/addRoom", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('HOTEL_ADMIN')")
	public ResponseEntity<Page<Room>> addRoom (Pageable pageable, @PathVariable Integer hotelId, @RequestBody Room r) throws ParseException {
		roomServices.save(hotelId, r);
		return new ResponseEntity<Page<Room>> (roomServices.findAll(hotelId, pageable), HttpStatus.OK);
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
	public ResponseEntity<Page<Room>> deleteRoom(Pageable pageable, @PathVariable Integer hotelId, @PathVariable Integer roomId)
	{
		roomServices.delete(hotelId, roomId);
		return new ResponseEntity<Page<Room>>(roomServices.findAll(hotelId, pageable),HttpStatus.OK);
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
		admin.setImage(imageServices.getUserPath(image, admin.getUsername()));

		hotel = hotelServices.save(hotel);
		
		imageServices.saveUserImg(image, admin.getUsername());
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
}

