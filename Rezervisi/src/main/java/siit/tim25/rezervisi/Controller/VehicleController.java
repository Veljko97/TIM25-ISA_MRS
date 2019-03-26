package siit.tim25.rezervisi.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import siit.tim25.rezervisi.Beans.Vehicle;
import siit.tim25.rezervisi.Services.VehicleServices;

@RestController
@RequestMapping(path="app/vehicles")
public class VehicleController {
	@Autowired
	VehicleServices vehicleServices;
	
	@PostMapping(path="/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> addVehicle(@RequestBody Vehicle vehicle){
		
		return new ResponseEntity<Boolean>(vehicleServices.save(vehicle), HttpStatus.OK);
	}
	
	@GetMapping(path="/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Vehicle>> returnAll(){
		return new ResponseEntity<ArrayList<Vehicle>>(vehicleServices.findAll(), HttpStatus.OK);
	}
}
