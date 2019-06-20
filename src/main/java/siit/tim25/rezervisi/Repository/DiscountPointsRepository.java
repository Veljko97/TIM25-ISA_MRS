package siit.tim25.rezervisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import siit.tim25.rezervisi.Beans.DiscountPoint;

public interface DiscountPointsRepository extends JpaRepository<DiscountPoint, Integer> {

	public DiscountPoint findByDiscountPercent(Double DiscountPercent);
	
}
