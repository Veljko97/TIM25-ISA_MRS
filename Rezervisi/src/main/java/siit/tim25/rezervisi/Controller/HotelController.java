package siit.tim25.rezervisi.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siit.tim25.rezervisi.Beans.Hotel;
import siit.tim25.rezervisi.Beans.Room;
import siit.tim25.rezervisi.Services.HotelServices;
import siit.tim25.rezervisi.Services.RoomServices;



@RestController
@RequestMapping(path="app/hotels")
public class HotelController {
	

	@Autowired
	private HotelServices hotelServices;
	
	@Autowired
	private RoomServices roomServices;
	
	@PostMapping(path="/addHotel", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Hotel>> addHotel(@RequestBody Hotel hotel)  {	
		hotelServices.save(hotel);
		return new ResponseEntity<List<Hotel>>(hotelServices.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(path="/showHotels", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Hotel>> showHotels()
	{
		return new ResponseEntity<List<Hotel>>(hotelServices.findAll(),HttpStatus.OK);
	}

	
	@PostMapping(path="/addRoom", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Room>> addRoom (HttpServletRequest request, @RequestBody Room room) {
		if(roomServices.findOneByRoomNumber(room.getRoomNumber())!=null)
		{
			return new ResponseEntity<List<Room>>(HttpStatus.BAD_REQUEST);
		}
		roomServices.save(room);
		return new ResponseEntity<List<Room>>(roomServices.findAll(),HttpStatus.OK);
	}
	
	@PostMapping(path="/editRoom", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Room> editRoom(HttpServletRequest request, @RequestBody Room modifiedRoom)
	{
		modifiedRoom.setRoomID(Integer.parseInt(request.getParameter("id")));
		Room room = roomServices.findOne(Integer.parseInt(request.getParameter("id")));
		modifiedRoom.setRoomNumber(room.getRoomNumber());
		modifiedRoom.setRoomDescription(room.getRoomDescription());
		modifiedRoom.setRoomCapacity(room.getRoomCapacity());
		return new ResponseEntity<Room>(roomServices.save(modifiedRoom), HttpStatus.OK);
	}
	
	
	@PostMapping(path="/removeRoom", produces = MediaType.APPLICATION_JSON_VALUE)
	public void removeRoom (HttpServletRequest request,String roomID) {
		Hotel hotel = hotelServices.findOne(Integer.parseInt(request.getParameter("id")));
		hotel.getRoomList().remove(roomID);
		hotelServices.save(hotel);
	}
	
	@GetMapping(path="/getRoom", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Room> getAirline(HttpServletRequest request)
	{
		return new ResponseEntity<Room>(roomServices.findOne(Integer.parseInt(request.getParameter("index"))),HttpStatus.OK);
	}
	
	@GetMapping(path="/showRooms", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Room>> showRooms(HttpServletRequest request) {
		return new ResponseEntity<List<Room>>(roomServices.findAll(), HttpStatus.OK);	
	}
}

