package siit.tim25.rezervisi.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import siit.tim25.rezervisi.Beans.DiscountPoint;
import siit.tim25.rezervisi.Beans.users.StandardUser;
import siit.tim25.rezervisi.Services.DiscountPointsServices;
import siit.tim25.rezervisi.Services.users.StandardUserServices;
import siit.tim25.rezervisi.security.TokenUtils;

@Controller
@RequestMapping(path = "/app/DiscountPoints")
public class DiscountPointsController {
	
	@Autowired
	private DiscountPointsServices dpServices;

	@Autowired
	private StandardUserServices suServices;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@RequestMapping(method = RequestMethod.POST, path="/editDiscounts", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
//	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<Boolean> editDiscounts(@RequestBody DiscountPoint dp)	{
		return new ResponseEntity<Boolean>(dpServices.save(dp) != null,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/findDiscounts", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<List<DiscountPoint>> findDiscounts()	{
		return new ResponseEntity<List<DiscountPoint>>(dpServices.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/deleteDiscounts/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
//	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<Void> deleteDiscounts(@PathVariable Integer id)	{
		dpServices.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/findMyDiscount", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<DiscountPoint> findMyDiscount(HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		StandardUser loggedUser = this.suServices.findByUsername(username);
		
		return new ResponseEntity<DiscountPoint>(dpServices.findByMyPoints(loggedUser.getDiscauntPoints()), HttpStatus.OK);
	}
}
