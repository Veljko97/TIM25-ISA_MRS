package siit.tim25.rezervisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import siit.tim25.rezervisi.Beans.RentACar;


public interface RentACarRepository extends JpaRepository<RentACar, Integer> {

	public RentACar findOneByRentACarName(String rentACarName);
}
