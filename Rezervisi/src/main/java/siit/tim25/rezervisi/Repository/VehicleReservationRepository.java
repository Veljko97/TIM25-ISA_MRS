package siit.tim25.rezervisi.Repository;

import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import siit.tim25.rezervisi.Beans.TicketStatus;
import siit.tim25.rezervisi.Beans.VehicleReservation;

public interface VehicleReservationRepository extends JpaRepository<VehicleReservation, Integer>{

	@Query("SELECT vr FROM VehicleReservation vr WHERE vr.vehicle.branch.service.rentACarID = :rentACarId AND vr.status = :status")
	public Page<VehicleReservation> findAllByStatus(@Param("rentACarId") Integer rentACarId, 
												 @Param("status") TicketStatus status,Pageable pageable);
	
	@Query("SELECT vr FROM VehicleReservation vr WHERE vr.Id = :id")
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public VehicleReservation lockReservation(@Param("id") Integer id);
}
