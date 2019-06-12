package siit.tim25.rezervisi.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import siit.tim25.rezervisi.Beans.TicketStatus;
import siit.tim25.rezervisi.Beans.Vehicle;
import siit.tim25.rezervisi.Beans.VehicleReservation;
import siit.tim25.rezervisi.Beans.users.StandardUser;
import siit.tim25.rezervisi.DTO.VehicleReportDTO;
import siit.tim25.rezervisi.DTO.VehicleReservationDTO;
import siit.tim25.rezervisi.Repository.VehicleRepository;
import siit.tim25.rezervisi.Repository.VehicleReservationRepository;

@Service
public class VehicleReservationServices {
	@Autowired
	private VehicleRepository vehicleRepository;

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
	
	public List<VehicleReportDTO> getDailyReport(Integer serviceId) {
		return vrRepository.getDailyReport(serviceId);
	}
	
	public List<VehicleReportDTO> getWeeklyReport(Integer serviceId) {
		return vrRepository.getWeeklyReport(serviceId);
	}
	
	public List<VehicleReportDTO> getMonthlyReport(Integer serviceId) {
		return vrRepository.getMonthlyReport(serviceId);
	}
	
	public Double getDatesReport(Integer serviceId, Date startDate, Date endDate) {
		return vrRepository.getDatesReport(serviceId, startDate, endDate);
	}
	
	
	public List<VehicleReservationDTO> findVehicleReservationsByUserId(Integer userId) {
		return vrRepository.findVehicleReservationsByUserId(userId);
	}
	
	public void deleteVehicleReservation(Integer vehicleId, Integer reservationId) {
		Vehicle v = vehicleRepository.findOne(vehicleId);
		VehicleReservation vr = null;
		for(VehicleReservation vReservation: v.getReservation()) {
			if (vReservation.getId() == reservationId) {
				vr = vReservation;
			}
		}
		vr.setVehicle(null);
		vr.setUser(null);
		v.getReservation().remove(vr);
		vehicleRepository.save(v);
		vrRepository.delete(vr);
	}
	
	public void reserveVehicle(Integer vehicleId, StandardUser u, Date start, Date end) {
		Vehicle v = vehicleRepository.findOne(vehicleId);
		VehicleReservation vr = new VehicleReservation(v, u, start, end, v.getPrice(), TicketStatus.ACCEPTED, new Date());
		u.getVehicleReservation().add(vr);
		this.save(vr);
	}
	
}
