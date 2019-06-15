package siit.tim25.rezervisi.Services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siit.tim25.rezervisi.Beans.Room;
import siit.tim25.rezervisi.Beans.RoomReservation;
import siit.tim25.rezervisi.Beans.Ticket;
import siit.tim25.rezervisi.Beans.TicketStatus;
import siit.tim25.rezervisi.Beans.users.StandardUser;
import siit.tim25.rezervisi.DTO.FastReservationDTO;
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
	@Autowired
	private TicketServices ticketServices;
	@Autowired
	private RoomServices roomServices;
	
	public RoomReservation save(RoomReservation rr) {
		return rrRepository.save(rr);
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
	
	@Transactional
	public void reserveRoom(Integer ticketId, Integer roomId, StandardUser u, FastReservationDTO res) {
		Ticket t = ticketServices.findOne(ticketId);
		roomServices.lockRoom(roomId);
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
		Room r = roomRepository.findOne(roomId);
		RoomReservation rr = new RoomReservation(r, u, start, end, r.getPrice(), TicketStatus.ACCEPTED, new Date());
		u.getRoomReservation().add(rr);
		this.save(rr);
	}
	
	@Transactional
	public boolean fastReserve(Integer roomId, FastReservationDTO res) {
		Room room = roomServices.lockRoom(roomId);
		Date endRes = new Date(res.getEnd());
		Date startRes = new Date(res.getStart());
		for(RoomReservation rr : room.getReservation()) {
			if(rr.getReservationStart().compareTo(startRes) <= 0 && rr.getReservationEnd().compareTo(startRes) >= 0) {
				return false;
			}
			if(rr.getReservationStart().compareTo(endRes) <= 0 && rr.getReservationEnd().compareTo(endRes) >= 0) {
				return false;
			}
			
			if(rr.getReservationStart().compareTo(startRes) >= 0 && rr.getReservationEnd().compareTo(endRes) <= 0) {
				return false;
			}
		}
		RoomReservation reservation = new RoomReservation();
		reservation.setPrice(res.getPrice());
		reservation.setRoom(room);
		reservation.setReservationStart(startRes);
		reservation.setReservationEnd(endRes);
		reservation.setStatus(TicketStatus.FAST);
		this.save(reservation);
		return true;
	}
	
	@Transactional
	public void takeFastReservation(StandardUser loggedUser, Integer resId)
	{
		RoomReservation res = this.lockReservation(resId);
		res.setUser(loggedUser);
		loggedUser.getRoomReservation().add(res);
		res.setStatus(TicketStatus.ACCEPTED);
		this.save(res);
	}
	
	
}
