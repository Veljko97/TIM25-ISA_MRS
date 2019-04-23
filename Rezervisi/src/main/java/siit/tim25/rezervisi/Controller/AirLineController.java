package siit.tim25.rezervisi.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.Beans.Destination;
import siit.tim25.rezervisi.Beans.Flight;
import siit.tim25.rezervisi.Beans.users.AirLineAdmin;
import siit.tim25.rezervisi.DTO.FlightDTO;
import siit.tim25.rezervisi.DTO.UserDTO;
import siit.tim25.rezervisi.Services.AirLineServices;
import siit.tim25.rezervisi.Services.AirplaneServices;
import siit.tim25.rezervisi.Services.DestinationServices;
import siit.tim25.rezervisi.Services.FlightServices;
import siit.tim25.rezervisi.Services.users.AuthorityServices;
import siit.tim25.rezervisi.security.model.TokenState;
import siit.tim25.rezervisi.security.model.User;

@RestController
@RequestMapping(path="app/airlines")
public class AirLineController {
	
	@Autowired
	private AirLineServices airLineServices;
	
	@Autowired
	private DestinationServices destinationServices;
	
	@Autowired
	private FlightServices flightServices;
	
	@Autowired
	private AirplaneServices airplaneServices;
	
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorityServices authorityServices;
	
	@RequestMapping(method = RequestMethod.POST,path="/addAirline", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<List<AirLine>> addAirline(@RequestBody AirLine airline)  {
		
		if(airLineServices.findOneByAirLineName(airline.getAirLineName()) != null)	{
			return new ResponseEntity<List<AirLine>>(HttpStatus.BAD_REQUEST);
		}
		airLineServices.save(airline);
		return new ResponseEntity<List<AirLine>>(airLineServices.findAll(),HttpStatus.OK);
	}
	
	@DeleteMapping(path="/deleteAirline/{id}")
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<List<AirLine>> deleteAirline(@PathVariable Integer id)
	{
		airLineServices.delete(id);
		return new ResponseEntity<List<AirLine>>(airLineServices.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(path="/showAirLines", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<AirLine>> showAirLines(Pageable pageable)  {
		return new ResponseEntity<Page<AirLine>>(airLineServices.findAll(pageable),HttpStatus.OK);
	}
	
	@GetMapping(path="/getAirline/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<AirLine> getAirline(@PathVariable Integer id)
	{
		return new ResponseEntity<AirLine>(airLineServices.findOne(id),HttpStatus.OK);
	}
	
	@PutMapping(path="/editAirline/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<AirLine> editAirline(@PathVariable Integer id, @RequestBody AirLine modifiedAirline)
	{
		modifiedAirline.setAirLineID(id);
		AirLine air = airLineServices.findOne(id);
		modifiedAirline.setAirlineEarning(air.getAirlineEarning());
		return new ResponseEntity<AirLine>(airLineServices.save(modifiedAirline), HttpStatus.OK);
	}
	

	@GetMapping(path="/{airlineId}/showDestinations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Destination>> getDestinations(@PathVariable Integer airlineId) {
		return new ResponseEntity<Set<Destination>>(destinationServices.findAll(airlineId),HttpStatus.OK);
	}
	
	@PostMapping(path="/{airlineId}/addDestination", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('AIRLINE_ADMIN')")
	public ResponseEntity<Set<Destination>> addDestination (@PathVariable Integer airlineId, @RequestBody Destination destination) {
		destinationServices.save(airlineId, destination);
		return new ResponseEntity<Set<Destination>> (destinationServices.findAll(airlineId), HttpStatus.OK);
	}
	
	@GetMapping(path="/{airlineId}/getDestination/{destinationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('AIRLINE_ADMIN')")
	public ResponseEntity<Destination> getDestination(@PathVariable Integer airlineId, @PathVariable Integer destinationId)
	{
		return new ResponseEntity<Destination>(destinationServices.findOne(airlineId, destinationId), HttpStatus.OK);
	}
	
	@PutMapping(path="/{airlineId}/editDestination/{destinationId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('AIRLINE_ADMIN')")
	public ResponseEntity<Destination> editDestination(@PathVariable Integer airlineId, @PathVariable Integer destinationId, @RequestBody Destination modifiedDestination)
	{
		modifiedDestination.setIdDestination(destinationId);
		destinationServices.update(airlineId, modifiedDestination);
		
		return new ResponseEntity<Destination>(modifiedDestination, HttpStatus.OK);
	}
	
	@DeleteMapping(path="{airlineId}/deleteDestination/{destinationId}")
	@PreAuthorize("hasRole('AIRLINE_ADMIN')")
	public ResponseEntity<Set<Destination>> deleteDestination(@PathVariable Integer airlineId, @PathVariable Integer destinationId)
	{
		destinationServices.delete(airlineId, destinationId);
		return new ResponseEntity<Set<Destination>>(destinationServices.findAll(airlineId),HttpStatus.OK);
	}
	
	
	@GetMapping(path="/{airlineId}/showFlights", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('AIRLINE_ADMIN')")
	public ResponseEntity<Set<FlightDTO>> getFlights(@PathVariable Integer airlineId) {
		return new ResponseEntity<Set<FlightDTO>>(flightServices.findAllAndConvert(airlineId),HttpStatus.OK);
	}
	
	@PostMapping(path="/{airlineId}/addFlight", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('AIRLINE_ADMIN')")
	public ResponseEntity<Set<FlightDTO>> addFlight (@PathVariable Integer airlineId, @RequestBody FlightDTO f) throws ParseException {
		Flight fl = f.convert(destinationServices.findAll(airlineId), airplaneServices.findAll());
		flightServices.save(airlineId, fl);
		return new ResponseEntity<Set<FlightDTO>> (flightServices.findAllAndConvert(airlineId), HttpStatus.OK);
	}
	
	@GetMapping(path="/{airlineId}/getFlight/{flightId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FlightDTO> getFlight(@PathVariable Integer airlineId, @PathVariable Integer flightId)
	{
		return new ResponseEntity<FlightDTO>(flightServices.findOneAndConvert(airlineId, flightId), HttpStatus.OK);
	}
	
	@PutMapping(path="/{airlineId}/editFlight/{flightId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('AIRLINE_ADMIN')")
	public ResponseEntity<Flight> editFlight(@PathVariable Integer airlineId, @PathVariable Integer flightId, @RequestBody FlightDTO modifiedFlight) throws ParseException
	{
		Flight f = modifiedFlight.convert(destinationServices.findAll(airlineId), airplaneServices.findAll());
		f.setIdFlight(flightId);
		flightServices.update(airlineId, f);
		
		return new ResponseEntity<Flight>(f, HttpStatus.OK);
	}
	
	@DeleteMapping(path="{airlineId}/deleteFlight/{flightId}")
	@PreAuthorize("hasRole('AIRLINE_ADMIN')")
	public ResponseEntity<Set<Flight>> deleteFlight(@PathVariable Integer airlineId, @PathVariable Integer flightId)
	{
		flightServices.delete(airlineId, flightId);
		return new ResponseEntity<Set<Flight>>(flightServices.findAll(airlineId),HttpStatus.OK);
	}
	
	@GetMapping(path="/showUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<ArrayList<UserDTO>> getUsers(@PathVariable Integer id){
		ArrayList<UserDTO> users = new ArrayList<UserDTO>();
		for(User us : airLineServices.findOne(id).getAdmins()) {
			users.add(new UserDTO(us, new TokenState()));
		}
		return new ResponseEntity<ArrayList<UserDTO>>(users,HttpStatus.OK);
	}
	
	@PostMapping(path="/addUser/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<Boolean> addUser(@PathVariable Integer id, @RequestBody UserDTO user){
		
		AirLine airline = airLineServices.findOne(id);
		AirLineAdmin admin = new AirLineAdmin();
		admin.setPassword(passwordEncoder.encode("123"));
		admin.setUsername(user.getUsername());
		admin.setFirstName(user.getFirstName());
		admin.setLastName(user.getLastName());
		admin.setEmail(user.getEmail());
		admin.setEnabled(true);
		admin.setConfirmed(false);
		admin.setAirLine(airline);
		airline.getAdmins().add(admin);
		admin.setAuthorities(Arrays.asList(authorityServices.findOne(3)));
		airLineServices.save(airline);
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
}
