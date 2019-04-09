package siit.tim25.rezervisi.Services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.Hotel;
import siit.tim25.rezervisi.Beans.HotelRoom;
import siit.tim25.rezervisi.Beans.Room;
import siit.tim25.rezervisi.Repository.HotelRepository;
import siit.tim25.rezervisi.Repository.RoomRepository;

@Component
public class RoomServices {
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	public Room save(Integer hotelId, Room room) {
		Hotel h = hotelRepository.findOne(hotelId);
		room.setHotel(h);
		for (Room hr: h.getRoomList()) {
			if (hr.getRoomID() == room.getRoomID()) {
				return null;
			}
		}
		h.getRoomList().add(room);
		hotelRepository.save(h);
		return room;
	}
	
	public List<Room> findAll(Integer hotelId){
		Hotel h = hotelRepository.findOne(hotelId);
		ArrayList<Room> list = new ArrayList<Room>();
		for(Room hr: h.getRoomList()) {
			list.add(hr);
		}
		return list;
	}
	
	public Room findOne(Integer hotelId, Integer roomId)
	{
		Hotel h = hotelRepository.findOne(hotelId);
		
		Room room = null;
		for(Room hr: h.getRoomList()) {
			if (hr.getRoomID() == roomId) {
				room = hr;
			}
		}
		return room;
	}
	
	public Room update(Integer hotelId, Room r) {
		System.out.println(r);
		Hotel h = hotelRepository.findOne(hotelId);
		Room room = null;
		for(Room hr: h.getRoomList()) {
			System.out.println(hr);
			if (hr.getRoomID() == r.getRoomID()) {
				hr.setRoomDescription(r.getRoomDescription());
				hr.setRoomNumber(r.getRoomNumber());
				hr.setPrice(r.getPrice());
				hr.setRoomCapacity(r.getRoomCapacity());
				room = hr;
			}
		}
		hotelRepository.save(h);
		System.out.println(room);
		// roomRepository.save(room);
		return room;
	}
	
	public void delete(Integer hotelId, Integer roomId) {
		Hotel h = hotelRepository.findOne(hotelId);
		Room hotelRoom = null;
		for(Room hr: h.getRoomList()) {
			if (hr.getRoomID() == roomId) {
				hotelRoom = hr;
			}
		}
		hotelRoom.setHotel(null);
		h.getRoomList().remove(hotelRoom);
		hotelRepository.save(h);
	}
	
}
