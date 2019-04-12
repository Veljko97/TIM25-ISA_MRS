package siit.tim25.rezervisi.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import siit.tim25.rezervisi.Beans.Destination;
import siit.tim25.rezervisi.Beans.RentACar;
import siit.tim25.rezervisi.Beans.RentACarBranch;
import siit.tim25.rezervisi.Services.BranchServices;
import siit.tim25.rezervisi.Services.RentACarServices;


@RestController
@RequestMapping(path="app/rentacar")
public class RentACarController {
	
	@Autowired
	private RentACarServices rentACarServices;
	
	@Autowired
	private BranchServices branchServices;
	
	@PostMapping(path="/addRentACar", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<List<RentACar>> addRentACar(@RequestBody RentACar rnt)  {
		if(rentACarServices.findOneByRentACarName(rnt.getRentACarName()) != null) {
			return new ResponseEntity<List<RentACar>>(HttpStatus.BAD_REQUEST);
		}
		rentACarServices.save(rnt);
		return new ResponseEntity<List<RentACar>>(rentACarServices.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(path="/showRentACars", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RentACar>> showRentACars()
	{
		return new ResponseEntity<List<RentACar>>(rentACarServices.findAll(), HttpStatus.OK);
	}
	
	@DeleteMapping(path="/deleteRentacar/{id}")
	public ResponseEntity<List<RentACar>> deleteRentACar(@PathVariable Integer id)
	{
		rentACarServices.delete(id);
		return new ResponseEntity<List<RentACar>>(rentACarServices.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(path="/showRentacar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RentACar>> showRentACar()
	{
		return new ResponseEntity<List<RentACar>>(rentACarServices.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(path="/getRentacar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RentACar> getRentacar(@PathVariable Integer id)
	{
		return new ResponseEntity<RentACar>(rentACarServices.findOne(id),HttpStatus.OK);
	}
	
	@PutMapping(path="/editRentacar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RentACar> editRentacar(@PathVariable Integer id, @RequestBody RentACar rentacar)
	{
		rentacar.setRentACarID(id);
		RentACar r = rentACarServices.findOne(id);
		rentacar.setRentACarAverageGrade(r.getRentACarAverageGrade());
		rentacar.setRentACarEarning(r.getRentACarEarning());
		return new ResponseEntity<RentACar>(rentACarServices.save(rentacar), HttpStatus.OK);
	}
	

	@GetMapping(path="/{rentacarId}/showBranches", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('RENTACAR_ADMIN')")
	public ResponseEntity<Set<RentACarBranch>> getBranches(@PathVariable Integer rentacarId) {
		return new ResponseEntity<Set<RentACarBranch>>(branchServices.findAll(rentacarId),HttpStatus.OK);
	}
	
	@PostMapping(path="/{rentacarId}/addBranch", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('RENTACAR_ADMIN')")
	public ResponseEntity<Set<RentACarBranch>> addBranch (@PathVariable Integer rentacarId, @RequestBody RentACarBranch branch) {
		branchServices.save(rentacarId, branch);
		return new ResponseEntity<Set<RentACarBranch>> (branchServices.findAll(rentacarId), HttpStatus.OK);
	}
	
	@GetMapping(path="/{rentacarId}/getBranch/{branchId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('RENTACAR_ADMIN')")
	public ResponseEntity<RentACarBranch> getBranch(@PathVariable Integer rentacarId, @PathVariable Integer branchId)
	{
		return new ResponseEntity<RentACarBranch>(branchServices.findOne(rentacarId, branchId), HttpStatus.OK);
	}
	
	@PutMapping(path="/{rentacarId}/editBranch/{branchId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('RENTACAR_ADMIN')")
	public ResponseEntity<RentACarBranch> editBranch(@PathVariable Integer rentacarId, @PathVariable Integer branchId, @RequestBody RentACarBranch modifiedBranch)
	{
		modifiedBranch.setIdBranch(branchId);
		branchServices.update(rentacarId, modifiedBranch);
		
		return new ResponseEntity<RentACarBranch>(modifiedBranch, HttpStatus.OK);
	}
	
	@DeleteMapping(path="{rentacarId}/deleteBranch/{branchId}")
	@PreAuthorize("hasRole('RENTACAR_ADMIN')")
	public ResponseEntity<Set<RentACarBranch>> deleteBranch(@PathVariable Integer rentacarId, @PathVariable Integer branchId)
	{
		branchServices.delete(rentacarId, branchId);
		return new ResponseEntity<Set<RentACarBranch>>(branchServices.findAll(rentacarId),HttpStatus.OK);
	}
	
}
