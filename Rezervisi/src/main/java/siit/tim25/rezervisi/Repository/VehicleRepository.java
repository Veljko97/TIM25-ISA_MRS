package siit.tim25.rezervisi.Repository;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.Vehicle;

@Component
public class VehicleRepository {
	
	private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	
	public boolean save(Vehicle vehicle) {
		vehicle.setId(vehicles.size());
		vehicles.add(vehicle);
		return true;
	}
	
	public ArrayList<Vehicle> findAll(){
		return vehicles;
	}
}
