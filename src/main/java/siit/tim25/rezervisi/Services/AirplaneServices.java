package siit.tim25.rezervisi.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.AirPlane;
import siit.tim25.rezervisi.Repository.AirplaneRepository;

@Component
public class AirplaneServices {
	@Autowired
	private AirplaneRepository airplaneRepository;
	
	public List<AirPlane> findAll(){
		return airplaneRepository.findAll();
	}
	
	public AirPlane findOne(Integer airLineID)
	{
		return airplaneRepository.findOne(airLineID);
	}
	
	
}
