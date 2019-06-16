package siit.tim25.rezervisi.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import siit.tim25.rezervisi.Beans.RentACar;
import siit.tim25.rezervisi.Beans.RentACarBranch;
import siit.tim25.rezervisi.Beans.TicketStatus;
import siit.tim25.rezervisi.Beans.Vehicle;
import siit.tim25.rezervisi.Beans.VehicleReservation;
import siit.tim25.rezervisi.Beans.users.StandardUser;
import siit.tim25.rezervisi.DTO.FastReservationDTO;
import siit.tim25.rezervisi.DTO.RentACarBranchDTO;
import siit.tim25.rezervisi.DTO.UserDTO;
import siit.tim25.rezervisi.DTO.VehicleDTO;
import siit.tim25.rezervisi.DTO.VehicleReservationDTO;
import siit.tim25.rezervisi.Services.BranchServices;
import siit.tim25.rezervisi.Services.RentACarServices;
import siit.tim25.rezervisi.Services.VehicleReservationServices;
import siit.tim25.rezervisi.Services.VehicleServices;
import siit.tim25.rezervisi.Services.users.StandardUserServices;
import siit.tim25.rezervisi.security.TokenUtils;
import siit.tim25.rezervisi.security.model.TokenState;
import siit.tim25.rezervisi.security.model.User;


@RestController
@RequestMapping(path="app/rentacar")
public class RentACarController {
	
	@Autowired
	private RentACarServices rentACarServices;
	
	@Autowired
	private BranchServices branchServices;
	
	@Autowired
	private VehicleServices vehicleServices;
	
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private VehicleReservationServices vrServices;
	
	@Autowired
	private StandardUserServices stdUserServices;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@PostMapping(path="/addRentACar", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<Page<RentACar>> addRentACar(Pageable pageable, @RequestParam("image") MultipartFile image,
													@RequestParam("model") String a )  {
		return rentACarServices.addService(image, a) ? new ResponseEntity<Page<RentACar>>(rentACarServices.findAll(pageable),HttpStatus.OK) : new ResponseEntity<Page<RentACar>>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(path="/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<RentACar>> searchAirline(Pageable pageable, @RequestParam String name, @RequestParam String destination) {
		return new ResponseEntity<Page<RentACar>>(rentACarServices.search(name, destination, pageable), HttpStatus.OK);
	}
	
	@GetMapping(path="/showRentACars", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<RentACar>> showRentACars(Pageable pageable)
	{
		return new ResponseEntity<Page<RentACar>>(rentACarServices.findAll(pageable), HttpStatus.OK);
	}
	
	@DeleteMapping(path="/deleteRentacar/{id}")
	public ResponseEntity<Page<RentACar>> deleteRentACar(Pageable pageable, @PathVariable Integer id)
	{
		rentACarServices.delete(id);
		return new ResponseEntity<Page<RentACar>>(rentACarServices.findAll(pageable),HttpStatus.OK);
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
		return new ResponseEntity<RentACar>(rentACarServices.save(rentacar), HttpStatus.OK);
	}
	
	
	@GetMapping(path="/{rentacarId}/showAllBranches", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<RentACarBranchDTO>> getAllBranches(Pageable pageable, @PathVariable Integer rentacarId) {
		return new ResponseEntity<Set<RentACarBranchDTO>>(branchServices.findAllAndConvert(rentacarId),HttpStatus.OK);
	}

	@GetMapping(path="/{rentacarId}/showBranches", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<RentACarBranchDTO>> getBranches(Pageable pageable, @PathVariable Integer rentacarId) {
		return new ResponseEntity<Page<RentACarBranchDTO>>(branchServices.findAllAndConvert(rentacarId, pageable),HttpStatus.OK);
	}
	
	@PostMapping(path="/{rentacarId}/addBranch", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('RENTACAR_ADMIN')")
	public ResponseEntity<Page<RentACarBranchDTO>> addBranch (Pageable pageable, @PathVariable Integer rentacarId, @RequestBody RentACarBranch branch) {
		branchServices.save(rentacarId, branch);
		return new ResponseEntity<Page<RentACarBranchDTO>> (branchServices.findAllAndConvert(rentacarId, pageable), HttpStatus.OK);
	}
	
	@GetMapping(path="/{rentacarId}/getBranch/{branchId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('RENTACAR_ADMIN')")
	public ResponseEntity<RentACarBranchDTO> getBranch(@PathVariable Integer rentacarId, @PathVariable Integer branchId)
	{
		return new ResponseEntity<RentACarBranchDTO>(branchServices.findOne(rentacarId, branchId).convert(), HttpStatus.OK);
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
	public ResponseEntity<Page<RentACarBranchDTO>> deleteBranch(Pageable pageable, @PathVariable Integer rentacarId, @PathVariable Integer branchId)
	{
		branchServices.delete(rentacarId, branchId);
		return new ResponseEntity<Page<RentACarBranchDTO>>(branchServices.findAllAndConvert(rentacarId, pageable),HttpStatus.OK);
	}
	
	
	@GetMapping(path="/{rentacarId}/showVehicles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<VehicleDTO>> getVehicles(Pageable pageable, @PathVariable Integer rentacarId) {
		return new ResponseEntity<Page<VehicleDTO>>(vehicleServices.findAllAndConvert(rentacarId, pageable), HttpStatus.OK);
	}
	
	@GetMapping(path="/{rentacarId}/showAllVehicles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<VehicleDTO>> getAllVehicles(@PathVariable Integer rentacarId) {
		return new ResponseEntity<Set<VehicleDTO>>(vehicleServices.findAllAndConvert(rentacarId), HttpStatus.OK);
	}
	
	@PostMapping(path="/{rentacarId}/addVehicle", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('RENTACAR_ADMIN')")
	public ResponseEntity<Page<VehicleDTO>> addVehicle(Pageable pageable, @PathVariable Integer rentacarId, @RequestBody VehicleDTO vehicle) throws ParseException {
		vehicleServices.save(rentacarId, vehicle.convert(branchServices.findAll(rentacarId)));
		return new ResponseEntity<Page<VehicleDTO>> (vehicleServices.findAllAndConvert(rentacarId, pageable), HttpStatus.OK);
	}
	
	@GetMapping(path="/{rentacarId}/getVehicle/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('RENTACAR_ADMIN')")
	public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Integer rentacarId, @PathVariable Integer vehicleId)
	{
		return new ResponseEntity<VehicleDTO>(vehicleServices.findOne(rentacarId, vehicleId).convert(), HttpStatus.OK);
	}
	
	@GetMapping(path="/getVehicle/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('RENTACAR_ADMIN')")
	public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Integer vehicleId)
	{
		return new ResponseEntity<VehicleDTO>(vehicleServices.findOne(vehicleId).convert(), HttpStatus.OK);
	}
	
	@PutMapping(path="/{rentacarId}/editVehicle/{vehicleId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('RENTACAR_ADMIN')")
	public ResponseEntity<VehicleDTO> editVehicle(@PathVariable Integer rentacarId, @PathVariable Integer vehicleId, @RequestBody VehicleDTO modifiedVehicle) throws ParseException
	{
		modifiedVehicle.setIdVehicle(vehicleId);
		vehicleServices.update(rentacarId, modifiedVehicle.convert(branchServices.findAll(rentacarId)));
		
		return new ResponseEntity<VehicleDTO>(modifiedVehicle, HttpStatus.OK);
	}
	
	@DeleteMapping(path="{rentacarId}/deleteVehicle/{vehicleId}")
	@PreAuthorize("hasRole('RENTACAR_ADMIN')")
	public ResponseEntity<Page<VehicleDTO>> deleteVehicle(Pageable pageable, @PathVariable Integer rentacarId, @PathVariable Integer vehicleId)
	{
		vehicleServices.delete(rentacarId, vehicleId);
		return new ResponseEntity<Page<VehicleDTO>>(vehicleServices.findAllAndConvert(rentacarId, pageable),HttpStatus.OK);
	}
	
	@PostMapping(path = "/{ticketId}/getAvailableVehicles")
	@PreAuthorize("hasRole('USER') or hasRole('HOTEL_ADMIN')")
	public ResponseEntity<Page<Vehicle>> getAvailableVehicles(@RequestBody FastReservationDTO res, @PathVariable Integer ticketId, Pageable pageable)	{
		
		return new ResponseEntity<Page<Vehicle>> (vehicleServices.findByDestination(ticketId, res, pageable), HttpStatus.OK);
	}
	
	@PostMapping(path = "/{ticketId}/reserve/{vehicleId}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER') or hasRole('HOTEL_ADMIN')")
	public ResponseEntity<VehicleReservationDTO> reserveVehicle(@RequestBody FastReservationDTO res, HttpServletRequest request, @PathVariable Integer vehicleId, @PathVariable Integer ticketId) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		StandardUser loggedUser = stdUserServices.findByUsername(username);
		VehicleReservationDTO vr = new VehicleReservationDTO(vrServices.reserveVehicle(ticketId, vehicleId, res, loggedUser));
		return new ResponseEntity<VehicleReservationDTO> (vr, HttpStatus.OK);
	}
	
	
	
	@GetMapping(path="/showUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<ArrayList<UserDTO>> getUsers(@PathVariable Integer id){
		ArrayList<UserDTO> users = new ArrayList<UserDTO>();
		for(User us : rentACarServices.findOne(id).getAdmins()) {
			users.add(new UserDTO(us, new TokenState()));
		}
		return new ResponseEntity<ArrayList<UserDTO>>(users,HttpStatus.OK);
	}
	
	@PostMapping(path="/addUser/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<Boolean> addUser(@PathVariable Integer id, @RequestParam("image") MultipartFile image,
											@RequestParam("model") String a) {
		
		return rentACarServices.addUser(image, a, id) ? new ResponseEntity<Boolean>(true,HttpStatus.OK) : new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(path = "/makeFastReservation/{vehicleId}")
	@PreAuthorize("hasRole('RENTACAR_ADMIN')")
	public ResponseEntity<Void> makeFastReservation(@RequestBody FastReservationDTO res,
													@PathVariable Integer vehicleId) {
		return vrServices.makeFastReservation(vehicleId, res) ? new ResponseEntity<Void>(HttpStatus.OK) : new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(path = "/takeFastReservation/{resId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Void> takeFastReservation(@PathVariable Integer resId, HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		StandardUser loggedUser = stdUserServices.findByUsername(username);
		vrServices.takeFastReservation(resId, loggedUser);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping(path = "/allFastReservation/{rentACarId}")
	@PreAuthorize("hasRole('USER') or hasRole('RENTACAR_ADMIN')")
	public ResponseEntity<Page<FastReservationDTO>> getAllFastTicket(@PathVariable Integer rentACarId,Pageable pageable)	{
		Page<VehicleReservation> reservations = vrServices.findAllByStatus(rentACarId, TicketStatus.FAST, pageable);
		List<FastReservationDTO> fasts = new ArrayList<FastReservationDTO>();
		for(VehicleReservation res : reservations) {
			fasts.add(new FastReservationDTO(res));
		}
		return new ResponseEntity<Page<FastReservationDTO>>(new PageImpl<FastReservationDTO>(fasts,reservations.nextPageable(),reservations.getTotalElements()),
												HttpStatus.OK);
	}
	

	@PostMapping(path="/freeVehicles/{serviceId}")
	@PreAuthorize("hasRole('RENTACAR_ADMIN')")
	public ResponseEntity<Page<VehicleDTO>> findFree(@RequestBody FastReservationDTO res, @PathVariable Integer serviceId, Pageable pageable){
		Page<Vehicle> vehicles = vehicleServices.findFree(serviceId, new Date(res.getStart()), new Date(res.getEnd()), pageable);
		
		Page<VehicleDTO> vDTO = vehicles.map(new Converter<Vehicle, VehicleDTO>() {

			@Override
			public VehicleDTO convert(Vehicle source) {
				return new VehicleDTO(source);
			}
		});
		return new ResponseEntity<Page<VehicleDTO>>(vDTO,HttpStatus.OK);
  }

	@DeleteMapping(path="/{serviceId}/cancelReservation/{vehicleId}")
	public ResponseEntity<Integer> cancelReservation(@PathVariable Integer serviceId, @PathVariable Integer vehicleId, @RequestBody List<Integer> ids){
		for(Integer id: ids) {
			this.vrServices.deleteVehicleReservation(vehicleId, id);
		}
		return new ResponseEntity<Integer>(-1, HttpStatus.NO_CONTENT);

	}
}
