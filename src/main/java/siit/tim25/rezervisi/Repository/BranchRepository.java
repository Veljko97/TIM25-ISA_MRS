package siit.tim25.rezervisi.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import siit.tim25.rezervisi.Beans.RentACarBranch;

public interface BranchRepository extends JpaRepository<RentACarBranch, Integer> {
	public RentACarBranch findOneByBranchName(String branchName);

	@Query("select b from RentACarBranch b where b.service.rentACarID = :id")
	public Page<RentACarBranch> findAllByServiceId(@Param("id") Integer airlineId, Pageable pageable);
}
