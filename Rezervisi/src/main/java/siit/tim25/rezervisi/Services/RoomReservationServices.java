package siit.tim25.rezervisi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import siit.tim25.rezervisi.Beans.RoomReservation;
import siit.tim25.rezervisi.Beans.TicketStatus;
import siit.tim25.rezervisi.Repository.RoomReservationRepository;

@Service
public class RoomReservationServices {

	@Autowired
	private RoomReservationRepository rrRepository;
	
	public RoomReservation save(RoomReservation rr) {
		return rrRepository.save(rr);
	}
	
	public Page<RoomReservation> findAllByStatus(Integer hotelId, TicketStatus status, Pageable pageable) {
		return rrRepository.findAllByStatus(hotelId, status, pageable);
	}
	
	public RoomReservation lockReservation(Integer id) {
		return rrRepository.lockReservation(id);
	}
}
