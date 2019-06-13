package siit.tim25.rezervisi.Repository;

import java.util.Date;

import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import siit.tim25.rezervisi.Beans.Vehicle;


public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
	@Query("select v from Vehicle v inner join v.branch b inner join b.service r where r.rentACarID = :id")
	public Page<Vehicle> findAllByServiceId(@Param("id") Integer serviceId, Pageable pageable);

	@Query("SELECT v FROM Vehicle v WHERE v.idVehicle = :id")
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public Vehicle lockVehicle(@Param("id") Integer id);
	
	@Query("SELECT v FROM Vehicle v WHERE v.branch.service.rentACarID = :serviceId AND 0 = (SELECT count(vr) FROM VehicleReservation vr WHERE vr.vehicle.idVehicle = v.idVehicle AND ((vr.reservationStart <= :startDate AND vr.reservationEnd >= :startDate) OR (vr.reservationStart <= :endDate AND vr.reservationEnd >= :endDate) OR (vr.reservationStart >= :startDate AND vr.reservationEnd <= :endDate)))")
	public Page<Vehicle> findFree(@Param("serviceId") Integer serviceId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);
	
	@Query("Select ve FROM Vehicle ve WHERE ve.idVehicle IN (SELECT vr.vehicle.idVehicle FROM ve.reservation vr WHERE vr.user.id = :userId AND vr.reservationEnd < :dateNow)")
	public Page<Vehicle> findPastVehicleReservations(@Param("userId") Integer userId, @Param("dateNow") Date dateNow , Pageable pageable);
}
