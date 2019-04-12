package siit.tim25.rezervisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import siit.tim25.rezervisi.Beans.RentACarBranch;

public interface BranchRepository extends JpaRepository<RentACarBranch, Integer> {
	public RentACarBranch findOneByBranchName(String branchName);

}
