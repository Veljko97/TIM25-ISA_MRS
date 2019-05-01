package siit.tim25.rezervisi.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import siit.tim25.rezervisi.Beans.RentACar;


public interface RentACarRepository extends JpaRepository<RentACar, Integer> {

	public RentACar findOneByRentACarName(String rentACarName);
	
	@Query("SELECT r FROM RentACar r WHERE (SELECT bd.destinationName FROM r.rentACarBranches br inner join br.destination bd) LIKE %?2% AND r.rentACarName LIKE %?1%")
	public Page<RentACar> findSearch(String rentACarName, String destinationName, Pageable pageable);
}
