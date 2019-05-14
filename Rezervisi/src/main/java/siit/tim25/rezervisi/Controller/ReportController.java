package siit.tim25.rezervisi.Controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
//Spring Framework
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import siit.tim25.rezervisi.Beans.Flight;
import siit.tim25.rezervisi.Services.FlightServices;
import siit.tim25.rezervisi.security.TokenUtils;
import siit.tim25.rezervisi.security.model.User;
import siit.tim25.rezervisi.security.servicce.UserService;
@Controller
@RequestMapping("/app/chart")
public class ReportController {

	@Autowired
	private FlightServices flightServices;
	
	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	private UserService userServices;	
	
	@RequestMapping(method = RequestMethod.GET)
	public String chart(ModelMap model) {
		return "chart";
	}
	@RequestMapping(value = "/getFlights", method = RequestMethod.POST)
	public @ResponseBody Set<Flight> getFlights(HttpServletRequest request) {
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
	    
		User user = this.userServices.findByUsername(username);
	  
		return flightServices.findAll(1);
	}
}