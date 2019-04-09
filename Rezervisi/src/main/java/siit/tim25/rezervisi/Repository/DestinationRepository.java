package siit.tim25.rezervisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import siit.tim25.rezervisi.Beans.Destination;


public interface DestinationRepository extends JpaRepository<Destination, Integer> {
	public Destination findOneByDestinationName(String destinationName);

}
