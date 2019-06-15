package siit.tim25.rezervisi.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import siit.tim25.rezervisi.Beans.Room;
import siit.tim25.rezervisi.Beans.RoomReservation;
import siit.tim25.rezervisi.Beans.TicketStatus;
import siit.tim25.rezervisi.Beans.users.StandardUser;
import siit.tim25.rezervisi.DTO.RoomReportDTO;
import siit.tim25.rezervisi.DTO.RoomReservationDTO;
import siit.tim25.rezervisi.Repository.RoomRepository;
import siit.tim25.rezervisi.Repository.RoomReservationRepository;

@Service
public class RoomReservationServices {

	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private RoomReservationRepository rrRepository;
	
	public RoomReservation save(RoomReservation rr) {
		return rrRepository.save(rr);
	}
	
	public RoomReservation findOne(Integer id) {
		return rrRepository.findOne(id);
	}
	
	public Page<RoomReservation> findAllByStatus(Integer hotelId, TicketStatus status, Pageable pageable) {
		return rrRepository.findAllByStatus(hotelId, status, pageable);
	}
	
	public RoomReservation lockReservation(Integer id) {
		return rrRepository.lockReservation(id);
	}
	
	public List<RoomReportDTO> getDailyReport(Integer hotelId) {
		return rrRepository.getDailyReport(hotelId);
	}
	
	public List<RoomReportDTO> getWeeklyReport(Integer hotelId) {
		return rrRepository.getWeeklyReport(hotelId);
	}
	
	public List<RoomReportDTO> getMonthlyReport(Integer hotelId) {
		return rrRepository.getMonthlyReport(hotelId);
	}
	
	public Double getDatesReport(Integer hotelId, Date startDate, Date endDate) {
		return rrRepository.getDatesReport(hotelId, startDate, endDate);
	}
	
	public List<RoomReservationDTO> findRoomReservationsByUserId(Integer userId) {
		return rrRepository.findRoomReservationsByUserId(userId);
	}
	
	public void deleteRoomReservation(Integer roomId, Integer reservationId) {
		Room r = roomRepository.findOne(roomId);
		RoomReservation rr = null;
		for(RoomReservation rReservation: r.getReservation()) {
			if (rReservation.getId() == reservationId) {
				rr = rReservation;
			}
		}
		rr.setRoom(null);
		rr.setUser(null);
		r.getReservation().remove(rr);
		roomRepository.save(r);
		rrRepository.delete(rr);
	}
	
	public RoomReservation reserveRoom(Integer roomId, StandardUser u, Date start, Date end) {
		Room r = roomRepository.findOne(roomId);
		RoomReservation rr = new RoomReservation(r, u, start, end, r.getPrice(), TicketStatus.ACCEPTED, new Date());
		u.getRoomReservation().add(rr);
		return this.save(rr);
	}
	
	
}
