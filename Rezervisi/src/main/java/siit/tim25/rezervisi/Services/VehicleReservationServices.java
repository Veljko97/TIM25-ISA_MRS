package siit.tim25.rezervisi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import siit.tim25.rezervisi.Beans.TicketStatus;
import siit.tim25.rezervisi.Beans.VehicleReservation;
import siit.tim25.rezervisi.Repository.VehicleReservationRepository;

@Service
public class VehicleReservationServices {
	
	@Autowired
	private VehicleReservationRepository vrRepository;
	
	public VehicleReservation save(VehicleReservation vr) {
		return vrRepository.save(vr);
	}
	
	public Page<VehicleReservation> findAllByStatus(Integer rentACarId, TicketStatus status, Pageable pageable) {
		return vrRepository.findAllByStatus(rentACarId, status, pageable);
	}
	
	public VehicleReservation lockReservation(Integer id) {
		return vrRepository.lockReservation(id);
	}
}
