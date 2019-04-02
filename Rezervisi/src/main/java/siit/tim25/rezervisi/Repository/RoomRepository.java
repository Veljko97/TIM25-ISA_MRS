package siit.tim25.rezervisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import siit.tim25.rezervisi.Beans.Room;


public interface RoomRepository extends JpaRepository<Room, Integer> {

	public Room findOneByRoomNumber(String roomNumber);
	
}
