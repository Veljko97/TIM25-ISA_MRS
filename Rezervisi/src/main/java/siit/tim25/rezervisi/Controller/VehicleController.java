package siit.tim25.rezervisi.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasRole('RENTACAR_ADMIN')")
	public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle){
		
		return new ResponseEntity<Vehicle>(vehicleServices.save(vehicle), HttpStatus.OK);
	}
	
	@GetMapping(path="/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Vehicle>> returnAll(){
		return new ResponseEntity<List<Vehicle>>(vehicleServices.findAll(), HttpStatus.OK);
	}
}
