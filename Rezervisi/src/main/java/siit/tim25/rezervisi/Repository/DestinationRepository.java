package siit.tim25.rezervisi.Repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import siit.tim25.rezervisi.Beans.Destination;


public interface DestinationRepository extends JpaRepository<Destination, Integer> {
	public Destination findOneByDestinationName(String destinationName);

	@Query("select d from Destination d where d.airLine.airLineID = :id")
	public Page<Destination> findAllByAirLineId(@Param("id") Integer airlineId, Pageable pageable);
}
