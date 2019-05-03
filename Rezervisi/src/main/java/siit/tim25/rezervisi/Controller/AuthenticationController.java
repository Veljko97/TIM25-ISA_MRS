package siit.tim25.rezervisi.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.Beans.users.AirLineAdmin;
import siit.tim25.rezervisi.Beans.users.HotelAdmin;
import siit.tim25.rezervisi.Beans.users.RentACarAdmin;
import siit.tim25.rezervisi.DTO.AirLineAdminDTO;
import siit.tim25.rezervisi.DTO.HotelAdminDTO;
import siit.tim25.rezervisi.DTO.RegistrationUserDTO;
import siit.tim25.rezervisi.DTO.RentACarAdminDTO;
import siit.tim25.rezervisi.DTO.UserDTO;
import siit.tim25.rezervisi.Services.users.AirLineAdminServices;
import siit.tim25.rezervisi.Services.users.AuthorityServices;
import siit.tim25.rezervisi.Services.users.HotelAdminServices;
import siit.tim25.rezervisi.Services.users.RentACarAdminServices;
import siit.tim25.rezervisi.security.TokenUtils;
import siit.tim25.rezervisi.security.auth.JwtAuthenticationRequest;
import siit.tim25.rezervisi.security.model.Authority;
import siit.tim25.rezervisi.security.model.TokenState;
import siit.tim25.rezervisi.security.model.User;
import siit.tim25.rezervisi.security.servicce.CustomUserDetailsService;
import siit.tim25.rezervisi.security.servicce.UserService;


@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
	
	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	@Lazy
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private AirLineAdminServices airLineAdminServices; 
	
	@Autowired
	private HotelAdminServices hotelAdminServices;
	
	@Autowired 
	private RentACarAdminServices  rentACarAdminServices;
	
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorityServices authorityServices;
	
	@Autowired
	private UserService userServices;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity logOut(HttpServletRequest request) {
		SecurityContextHolder.getContext().setAuthentication(null);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response, Device device) throws AuthenticationException, IOException {

		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user.getUsername(), device);
		int expiresIn = tokenUtils.getExpired();
		Integer userId = user.getId();
		for(Object a : user.getAuthorities()) {
			Authority auth = (Authority) a;
			if(auth.getName().equals("ROLE_AIRLINE_ADMIN")) {
				user = airLineAdminServices.findOne(userId);
				return ResponseEntity.ok(new AirLineAdminDTO((AirLineAdmin)user, new TokenState(jwt, expiresIn)));
			}else if (auth.getName().equals("ROLE_HOTEL_ADMIN")) {
				user = hotelAdminServices.findOne(userId);
				return ResponseEntity.ok(new HotelAdminDTO((HotelAdmin)user, new TokenState(jwt, expiresIn)));
			}else if(auth.getName().equals("ROLE_RENTACAR_ADMIN")) {
				user = rentACarAdminServices.findOne(userId);
				return ResponseEntity.ok(new RentACarAdminDTO((RentACarAdmin) user, new TokenState(jwt, expiresIn)));
			}else {
				return ResponseEntity.ok(new UserDTO(user, new TokenState(jwt, expiresIn)));
			}
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping(value="/registration", consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addUser(@RequestBody @Valid RegistrationUserDTO user) {
		 UserDTO udto = userServices.create(user);
		 if(udto!=null) {
			 System.out.println(udto);
			 return new ResponseEntity<>(udto,HttpStatus.CREATED);	 
		 }
		 return new ResponseEntity<>(udto,HttpStatus.CONFLICT);
	 }

	
	@PutMapping(path="/editUserProfile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> editUser(@RequestBody UserDTO modifiedUser,HttpServletRequest request, Device device) //prima izmenjenog korisnika kojeg saljem sa fronta
	{ 		
		
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
	    
		User user = this.userServices.findByUsername(username);
	  
	    List<User> allUsers = userServices.findAll();
	    for (User u : allUsers) {
			if(user.getEmail().equalsIgnoreCase(modifiedUser.getEmail())){
				return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
			}
		}
	    user.setUsername(modifiedUser.getUsername());
	    user.setEmail(modifiedUser.getEmail());
	    user = this.userServices.save(user);
	    return new ResponseEntity<UserDTO>(new UserDTO(user, new TokenState(tokenUtils.generateToken(user.getUsername(), device),tokenUtils.getExpired())), HttpStatus.OK); 
	}
		
	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
	    User user = (User) this.userDetailsService.loadUserByUsername(username);

		Device device = tokenUtils.getCurrentDevice(request);

		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			String refreshedToken = tokenUtils.refreshToken(token, device);
			int expiresIn = tokenUtils.getExpired();

			return ResponseEntity.ok(new TokenState(refreshedToken, expiresIn));
		} else {
			return ResponseEntity.badRequest().body(new TokenState());
		}
	}
	
	@RequestMapping(value = "/first-change-password", method = RequestMethod.POST)
	public ResponseEntity<?> firstChangePassword(@RequestBody PasswordChanger passwordChanger) {
		userDetailsService.changePassword("123", passwordChanger.newPassword);
		
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return ResponseEntity.accepted().body(result);
	}
	
	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
		userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
		
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return ResponseEntity.accepted().body(result);
	}

	static class PasswordChanger {
		public String oldPassword;
		public String newPassword;
	}
	
	@GetMapping(path="/showUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<ArrayList<UserDTO>> getUsers(){
		ArrayList<UserDTO> users = new ArrayList<UserDTO>();
		for(User us : userServices.findbyRole("ROLE_SYS_ADMIN")) {
			users.add(new UserDTO(us, new TokenState()));
		}
		return new ResponseEntity<ArrayList<UserDTO>>(users,HttpStatus.OK);
	}
	
	@PostMapping(path="/addUser",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<Boolean> addUser(@RequestBody UserDTO user){
		
		User admin = new AirLineAdmin();
		admin.setPassword(passwordEncoder.encode("123"));
		admin.setUsername(user.getUsername());
		admin.setFirstName(user.getFirstName());
		admin.setLastName(user.getLastName());
		admin.setEmail(user.getEmail());
		admin.setEnabled(true);
		admin.setConfirmed(false);
		admin.setAuthorities(Arrays.asList(authorityServices.findOne(1)));
		userServices.save(admin);
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	
}
