package siit.tim25.rezervisi.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import siit.tim25.rezervisi.Beans.DiscountPoint;
import siit.tim25.rezervisi.Repository.DiscountPointsRepository;

@Service
public class DiscountPointsServices {
	
	@Autowired
	private DiscountPointsRepository dpRepository;
	
	public List<DiscountPoint> findAll(){
		return dpRepository.findAll(new Sort(Direction.ASC, "discountPercent"));
	}
	
	public DiscountPoint save(DiscountPoint dp) {
		List<DiscountPoint> dps = dpRepository.findAll();
		if(dps.isEmpty())	{
			return dpRepository.saveAndFlush(dp);
		}
		DiscountPoint temp;
		if(( temp = dpRepository.findByDiscountPercent(dp.getDiscountPercent())) != null) {
			dp.setId(temp.getId());
		}
		for(int i = 0; i < dps.size(); i++) {
			DiscountPoint dpOne = dps.get(i);
			if(dpOne.getDiscountPercent().equals(dp.getDiscountPercent())) {
				continue;
			}
			if(dpOne.getDiscountPercent() < dp.getDiscountPercent()) {
				if(dpOne.getPointsNeeded() >= dp.getPointsNeeded()) {
					return null;
				}
			}else {
				if(dpOne.getPointsNeeded() <= dp.getPointsNeeded()) {
					return null;
				}
			}
		}
		return dpRepository.saveAndFlush(dp);
	}
	
	public void delete(Integer id) {
		dpRepository.delete(id);
	}
	
	public DiscountPoint findByMyPoints(Integer myPoints) {
		List<DiscountPoint> dps = this.findAll();
		if(dps.size() == 0) {
			DiscountPoint dp = new DiscountPoint();
			dp.setId(-1);
			return dp;
		}
		for(int i = 0; i < dps.size(); i++) {
			DiscountPoint dp = dps.get(i);
			if(dp.getPointsNeeded().equals(myPoints)) {
				return dp;
			}
			if(dp.getPointsNeeded().compareTo(myPoints) > 0) {
				if(i == 0) {
					dp = new DiscountPoint();
					dp.setId(-1);
					return dp;
				}
				return dps.get(i-1);
			}
		}
		return dps.get(dps.size()-1);
	}
}
