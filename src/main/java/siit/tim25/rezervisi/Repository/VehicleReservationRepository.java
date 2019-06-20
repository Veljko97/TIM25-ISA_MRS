package siit.tim25.rezervisi.Repository;

import java.util.Date;
import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import siit.tim25.rezervisi.Beans.TicketStatus;
import siit.tim25.rezervisi.Beans.VehicleReservation;
import siit.tim25.rezervisi.DTO.VehicleReportDTO;
import siit.tim25.rezervisi.DTO.VehicleReservationDTO;

public interface VehicleReservationRepository extends JpaRepository<VehicleReservation, Integer>{

	@Query("SELECT vr FROM VehicleReservation vr WHERE vr.vehicle.branch.service.rentACarID = :rentACarId AND vr.status = :status")
	public Page<VehicleReservation> findAllByStatus(@Param("rentACarId") Integer rentACarId, 
												 @Param("status") TicketStatus status,Pageable pageable);
	
	@Query("SELECT vr FROM VehicleReservation vr WHERE vr.Id = :id")
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public VehicleReservation lockReservation(@Param("id") Integer id);
	
	@Query("SELECT new siit.tim25.rezervisi.DTO.VehicleReportDTO(vr, SUM (vr.price), week(vr.made)) from VehicleReservation vr inner join vr.vehicle v inner join v.branch b inner join b.service s WHERE s.rentACarID = ?1 GROUP BY week(vr.made)")
	public List<VehicleReportDTO> getWeeklyReport(Integer serviceId);
	
	@Query("SELECT new siit.tim25.rezervisi.DTO.VehicleReportDTO(vr, SUM (vr.price), vr.made) from VehicleReservation vr inner join vr.vehicle v inner join v.branch b inner join b.service s WHERE s.rentACarID = ?1 GROUP BY month(vr.made)")
	public List<VehicleReportDTO> getMonthlyReport(Integer serviceId);
	
	@Query("SELECT new siit.tim25.rezervisi.DTO.VehicleReportDTO(vr, SUM (vr.price), vr.made) from VehicleReservation vr inner join vr.vehicle v inner join v.branch b inner join b.service s WHERE s.rentACarID = ?1 GROUP BY day(vr.made)")
	public List<VehicleReportDTO> getDailyReport(Integer serviceId);
	
	@Query("SELECT sum(vr.price) from VehicleReservation vr inner join vr.vehicle v inner join v.branch b inner join b.service s WHERE s.rentACarID = ?1 and (?2 is null or vr.made >= ?2) and (?3 is null or vr.made <= ?3)")
	public Double getDatesReport(Integer serviceId, Date startDate, Date endDate);
	
	@Query("SELECT new siit.tim25.rezervisi.DTO.VehicleReservationDTO(vr) from VehicleReservation vr inner join vr.user u WHERE u.id = ?1 and vr.status = '0'")
	public List<VehicleReservationDTO> findVehicleReservationsByUserId(Integer userId);
}
