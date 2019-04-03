package siit.tim25.rezervisi.Services;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import siit.tim25.rezervisi.Beans.Room;
import siit.tim25.rezervisi.Repository.RoomRepository;

@Component
public class RoomServices {
	@Autowired
	private RoomRepository roomRepository;
	
	public Room save(Room room) {
		return roomRepository.save(room);
	}
	
	public List<Room> findAll(){
		return roomRepository.findAll();
	}
	
	public Room findOne(Integer roomID)
	{
		return roomRepository.findOne(roomID);
	}
	
	public Room findOneByRoomNumber(String roomNumber) {
		return roomRepository.findOneByRoomNumber(roomNumber);
	}
	
	public Room update(Room room) {
		return roomRepository.save(room);
	}
	
}
