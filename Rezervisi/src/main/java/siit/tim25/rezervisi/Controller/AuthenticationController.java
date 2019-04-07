package siit.tim25.rezervisi.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import siit.tim25.rezervisi.Beans.users.AirLineAdmin;
import siit.tim25.rezervisi.Beans.users.HotelAdmin;
import siit.tim25.rezervisi.Beans.users.RentACarAdmin;
import siit.tim25.rezervisi.DTO.AirLineAdminDTO;
import siit.tim25.rezervisi.DTO.HotelAdminDTO;
import siit.tim25.rezervisi.DTO.RentACarAdminDTO;
import siit.tim25.rezervisi.Services.RentACarServices;
import siit.tim25.rezervisi.Services.users.AirLineAdminServices;
import siit.tim25.rezervisi.Services.users.HotelAdminServices;
import siit.tim25.rezervisi.Services.users.RentACarAdminServices;
import siit.tim25.rezervisi.security.TokenUtils;
import siit.tim25.rezervisi.security.auth.JwtAuthenticationRequest;
import siit.tim25.rezervisi.security.model.TokenState;
import siit.tim25.rezervisi.security.model.User;
import siit.tim25.rezervisi.security.servicce.CustomUserDetailsService;


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
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity logOut(HttpServletRequest request) {
		request.getSession().invalidate();
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
		if(airLineAdminServices.exists(userId)) {
			user = airLineAdminServices.findOne(userId);
			return ResponseEntity.ok(new AirLineAdminDTO((AirLineAdmin)user, new TokenState(jwt, expiresIn)));
		}else if (hotelAdminServices.exists(userId)) {
			user = hotelAdminServices.findOne(userId);
			return ResponseEntity.ok(new HotelAdminDTO((HotelAdmin)user, new TokenState(jwt, expiresIn)));
		}else{ //(rentACarAdminServices.exists(userId)) 
			user = rentACarAdminServices.findOne(userId);
			return ResponseEntity.ok(new RentACarAdminDTO((RentACarAdmin) user, new TokenState(jwt, expiresIn)));
		}

		
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
}
