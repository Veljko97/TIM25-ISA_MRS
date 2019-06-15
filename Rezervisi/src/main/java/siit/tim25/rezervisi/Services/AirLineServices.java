package siit.tim25.rezervisi.Services;


import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.Beans.users.AirLineAdmin;
import siit.tim25.rezervisi.DTO.UserDTO;
import siit.tim25.rezervisi.Repository.AirLineRepository;
import siit.tim25.rezervisi.Services.users.AuthorityServices;

@Component
public class AirLineServices {
	@Autowired
	private AirLineRepository airLineRepository;
	
	@Autowired
	private ImageServices imageServices;
	
	@Autowired
	private AirLineServices airLineServices;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorityServices authorityServices;
	
	public AirLine save(AirLine airline) {
		return airLineRepository.save(airline);
	}
	
	public Page<AirLine> findAll(Pageable pageable){
		return airLineRepository.findAll(pageable);
	}
	
	public List<AirLine> findAll(){
		return airLineRepository.findAll();
	}
	
	public Page<AirLine> findByName(String airLineName, Pageable pageable) {
		return airLineRepository.findByAirLineName(airLineName, pageable);
	}
	
	
	public AirLine findOne(Integer airLineID)
	{
		return airLineRepository.findOne(airLineID);
	}
	
	public AirLine findOneByAirLineName(String airLineName) {
		return airLineRepository.findOneByAirLineName(airLineName);
	}
	
	public AirLine update(AirLine airline) {
		return airLineRepository.save(airline);
	}
	
	public void delete(Integer id) {
		airLineRepository.delete(id);
	}
	
	public Page<AirLine> findPastAirLineReservations(Integer userId, Pageable pageable) {
		return airLineRepository.findPastAirLineReservations(userId, new Date(), pageable);
	}
	
	
	public boolean addAirLine(@RequestParam("image") MultipartFile image,
			@RequestParam("model") String a ) {
		AirLine airline = new AirLine();
		try {
			airline = mapper.readValue(a, AirLine.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(this.findOneByAirLineName(airline.getAirLineName()) != null)	{
			return false;
		}
		AirLine air = this.save(airline);
		String imgPath = imageServices.saveAirlineImg(image, air.getAirLineID());
		if(imgPath.equals("")) {
			airLineServices.delete(air.getAirLineID());
			return false;
		}
		air.setImage(imgPath);
		this.save(air);
		return true;
	}
	
	public void addAirLineUser(Integer id, MultipartFile image, String a ) throws IOException {
		UserDTO user;
		user = mapper.readValue(a, UserDTO.class);
		
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
		admin.setImage(imageServices.getUserPath(image, admin.getUsername()));
		
		airline = airLineServices.save(airline);
		
		imageServices.saveUserImg(image, admin.getUsername());
	}
	
}
