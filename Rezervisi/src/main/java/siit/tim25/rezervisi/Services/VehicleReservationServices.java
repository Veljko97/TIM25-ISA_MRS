package siit.tim25.rezervisi.Services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siit.tim25.rezervisi.Beans.Ticket;
import siit.tim25.rezervisi.Beans.TicketStatus;
import siit.tim25.rezervisi.Beans.Vehicle;
import siit.tim25.rezervisi.Beans.VehicleReservation;
import siit.tim25.rezervisi.Beans.users.StandardUser;
import siit.tim25.rezervisi.DTO.FastReservationDTO;
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
	
	@Autowired
	private VehicleServices vehicleServices;
	
	@Autowired
	private TicketServices ticketServices;
	
	public VehicleReservation save(VehicleReservation vr) {
		return vrRepository.save(vr);
	}
	
	public VehicleReservation findOne(Integer id) {
		return vrRepository.findOne(id);
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

	@Transactional
	public VehicleReservation reserveVehicle(Integer vehicleId, StandardUser u, Date start, Date end) {
		Vehicle v = vehicleRepository.findOne(vehicleId);
		VehicleReservation vr = new VehicleReservation(v, u, start, end, v.getPrice(), TicketStatus.ACCEPTED, new Date());
		u.getVehicleReservation().add(vr);
		return this.save(vr);
	}
	
	@Transactional
	public VehicleReservation reserveVehicle(Integer ticketId, Integer vehicleId, FastReservationDTO res, StandardUser loggedUser) {
		Ticket t = ticketServices.findOne(ticketId);
		vehicleServices.lockVehicle(vehicleId);
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
		
		return this.reserveVehicle(vehicleId, loggedUser, start, end);
	}
	
	@Transactional
	public boolean makeFastReservation(Integer vehicleId, FastReservationDTO res) {
		Vehicle vehicle = vehicleServices.lockVehicle(vehicleId);
		Date endRes = new Date(res.getEnd());
		Date startRes = new Date(res.getStart());
		for(VehicleReservation vr : vehicle.getReservation()) {
			if(vr.getReservationStart().compareTo(startRes) <= 0 && vr.getReservationEnd().compareTo(startRes) >= 0) {
				return false;
			}
			if(vr.getReservationStart().compareTo(endRes)<= 0 && vr.getReservationEnd().compareTo(endRes) >= 0) {
				return false;
			}
			if(vr.getReservationStart().compareTo(startRes) >= 0 && vr.getReservationEnd().compareTo(endRes) <= 0) {
				return false;
			}
		}
		VehicleReservation reservation = new VehicleReservation();
		reservation.setPrice(res.getPrice());
		reservation.setVehicle(vehicle);
		reservation.setReservationStart(startRes);
		reservation.setReservationEnd(endRes);
		reservation.setStatus(TicketStatus.FAST);
		this.save(reservation);
		return true;
	}
	
	@Transactional
	public void takeFastReservation(Integer resId, StandardUser loggedUser)
	{
		VehicleReservation res = this.lockReservation(resId);
		res.setUser(loggedUser);
		loggedUser.getVehicleReservation().add(res);
		res.setStatus(TicketStatus.ACCEPTED);
		this.save(res);
	}
}
