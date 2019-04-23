package siit.tim25.rezervisi.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.Repository.AirLineRepository;

@Component
public class AirLineServices {
	@Autowired
	private AirLineRepository airLineRepository;
	
	public AirLine save(AirLine airline) {
		return airLineRepository.save(airline);
	}
	
	public Page<AirLine> findAll(Pageable pageable){
		return airLineRepository.findAll(pageable);
	}
	
	public List<AirLine> findAll(){
		return airLineRepository.findAll();
	}
	
	
	public AirLine findOne(Integer airLineID)
	{
		return airLineRepository.findOne(airLineID);
	}
	
	public AirLine findOneByAirLineName(String airLineName) {
		return airLineRepository.findOneByAirLineName(airLineName);
	}
	
	public AirLine update(AirLine airline) {
		return airLineRepository.save(airline);
	}
	
	public void delete(Integer id) {
		airLineRepository.delete(id);
	}
	
}
