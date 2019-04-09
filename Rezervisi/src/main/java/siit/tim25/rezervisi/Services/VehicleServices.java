package siit.tim25.rezervisi.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.Vehicle;
import siit.tim25.rezervisi.Repository.VehicleRepository;

@Component
public class VehicleServices {

	@Autowired
	VehicleRepository vehicleRepository;
	
	public Vehicle save(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}
	
	public List<Vehicle> findAll(){
		return vehicleRepository.findAll();
	}
	
}
