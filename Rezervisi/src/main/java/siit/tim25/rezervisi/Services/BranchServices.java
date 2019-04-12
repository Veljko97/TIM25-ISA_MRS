package siit.tim25.rezervisi.Services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.RentACar;
import siit.tim25.rezervisi.Beans.RentACarBranch;
import siit.tim25.rezervisi.Repository.RentACarRepository;
import siit.tim25.rezervisi.Repository.BranchRepository;

@Component
public class BranchServices {
	@Autowired
	private RentACarRepository rentacarRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	public RentACarBranch save(Integer serviceId, RentACarBranch b) {
		RentACar r = rentacarRepository.findOne(serviceId);
		b.setService(r);
		r.getRentACarBranches().add(b);
		rentacarRepository.save(r);
		return b;
	}
	
	public Set<RentACarBranch> findAll(Integer serviceId){
		RentACar r = rentacarRepository.findOne(serviceId);

		return r.getRentACarBranches();
	}
	
	public RentACarBranch findOne(Integer serviceId, Integer branchId)
	{
		RentACar r = rentacarRepository.findOne(serviceId);
		RentACarBranch b = null;
		for(RentACarBranch br: r.getRentACarBranches()) {
			if (br.getIdBranch() == branchId) {
				b = br;
			}
		}
		return b;
	}
	
	public RentACarBranch update(Integer serviceId, RentACarBranch branch) {
		RentACar r = rentacarRepository.findOne(serviceId);
		RentACarBranch b = null;
		for(RentACarBranch br: r.getRentACarBranches()) {
			if (br.getIdBranch() == branch.getIdBranch()) {
				br.setBranchName(branch.getBranchName());
				br.setBranchAddress(branch.getBranchAddress());
				b = br;
			}
			
		}
		rentacarRepository.save(r);
		return b;
	}
	
	public void delete(Integer serviceId, Integer branchId) {
		RentACar r = rentacarRepository.findOne(serviceId);
		RentACarBranch b = null;
		for(RentACarBranch br: r.getRentACarBranches()) {
			if (br.getIdBranch() == branchId) {
				b = br;
			}
		}
		b.setService(null);
		r.getRentACarBranches().remove(b);
		rentacarRepository.save(r);
		branchRepository.delete(b);
	}
}
