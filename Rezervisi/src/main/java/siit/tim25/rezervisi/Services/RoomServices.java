package siit.tim25.rezervisi.Services;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.Hotel;
import siit.tim25.rezervisi.Beans.Room;
import siit.tim25.rezervisi.Beans.Ticket;
import siit.tim25.rezervisi.DTO.FastReservationDTO;
import siit.tim25.rezervisi.DTO.RoomDTO;
import siit.tim25.rezervisi.Repository.HotelRepository;
import siit.tim25.rezervisi.Repository.RoomRepository;

@Component
public class RoomServices {
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private TicketServices ticketServices;
	
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
	
	public Room findOne(Integer id) {
		return roomRepository.findOne(id);
	}
	
	public List<Room> findAll(Integer hotelId){
		Hotel h = hotelRepository.findOne(hotelId);
		ArrayList<Room> list = new ArrayList<Room>();
		for(Room hr: h.getRoomList()) {
			list.add(hr);
		}
		return list;
	}
	
	public Page<Room> findAll(Integer hotelId, Pageable pageable) {
		return roomRepository.findAllByHotelID(hotelId, pageable);
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
		Hotel h = hotelRepository.findOne(hotelId);
		Room room = null;
		for(Room hr: h.getRoomList()) {
			if (hr.getRoomID() == r.getRoomID()) {
				hr.setRoomDescription(r.getRoomDescription());
				hr.setRoomNumber(r.getRoomNumber());
				hr.setPrice(r.getPrice());
				hr.setRoomCapacity(r.getRoomCapacity());
				room = hr;
			}
		}
		hotelRepository.save(h);
		// roomRepository.save(room);
		return room;
	}
	
	public Page<Room> findByDestination(Integer ticketId, FastReservationDTO res, Pageable pageable) {
		Ticket t = ticketServices.findOne(ticketId);
		Date start = res.getStart() == 0 ?  t.getFlight().getLandingDate() : new Date(res.getStart());
		Date end = null;
		if (res.getEnd() == 0) {
			Calendar c = Calendar.getInstance();
			c.setTime(t.getFlight().getLandingDate());
			c.add(Calendar.DATE, 7);
			end = c.getTime();
		} else {
			end = new Date(res.getEnd());
		}
		return roomRepository.findByDestination(t.getFlight().getFinalDestination().getIdDestination(), start, end, pageable);
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
	
	public Room lockRoom(Integer id) {
		return roomRepository.lockRoom(id);
	}
	
	public Page<Room> findFree(Integer  hotelId, Date start, Date end, Pageable pageable) {
		return roomRepository.findFree(hotelId, start, end, pageable);
	}
	
	public Page<RoomDTO> findPastRoomReservations(Integer userId, Pageable pageable) {
		Page<Room> ro = roomRepository.findPastRoomReservations(userId, new Date(), pageable);
		return ro.map(new Converter<Room, RoomDTO>(){

			@Override
			public RoomDTO convert(Room source) {
				return new RoomDTO(source);
			}});
	}
}
