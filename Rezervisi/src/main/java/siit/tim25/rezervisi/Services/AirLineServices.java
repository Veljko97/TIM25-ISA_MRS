package siit.tim25.rezervisi.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.Repository.AirLineRepository;

@Component
public class AirLineServices {
	@Autowired
	private AirLineRepository airLineRepository;
	
	public boolean save(AirLine airline) {
		return airLineRepository.save(airline);
	}
	
	public ArrayList<AirLine> getAirLineList(){
		return airLineRepository.getAirlineList();
	}
	
	public AirLine getAirLine(Integer airLineID)
	{
		return airLineRepository.getAirLine(airLineID);
	}
	
}
