package siit.tim25.rezervisi.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import siit.tim25.rezervisi.Beans.RentACarBranch;
import siit.tim25.rezervisi.Beans.Vehicle;


public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
	@Query("select v from Vehicle v inner join v.branch b inner join b.service r where r.rentACarID = :id")
	public Page<Vehicle> findAllByServiceId(@Param("id") Integer serviceId, Pageable pageable);

}
