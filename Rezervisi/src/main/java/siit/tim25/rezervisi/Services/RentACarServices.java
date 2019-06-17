package siit.tim25.rezervisi.Services;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import siit.tim25.rezervisi.Beans.RentACar;
import siit.tim25.rezervisi.Beans.users.RentACarAdmin;
import siit.tim25.rezervisi.DTO.RentACarDTO;
import siit.tim25.rezervisi.DTO.UserDTO;
import siit.tim25.rezervisi.Repository.RentACarRepository;
import siit.tim25.rezervisi.Services.users.AuthorityServices;


@Component
public class RentACarServices {
	@Autowired
	private RentACarRepository rentACarRepository;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private DestinationServices destinationServices;
	
	@Autowired
	private ImageServices imageServices;
	
	@Autowired
	private AuthorityServices authorityServices;
	
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ProducerServices producerServices;
	
	public RentACar save(RentACar rnt) {
		return rentACarRepository.save(rnt);
	}
	
	public List<RentACar> findAll(){
		return rentACarRepository.findAll();
	}
	
	public Page<RentACar> findAll(Pageable pageable){
		return rentACarRepository.findAll(pageable);
	}
	
	public Page<RentACar> search(String rentACarName, String destinationName, Pageable pageable){
		return rentACarRepository.findSearch(rentACarName, destinationName, pageable);
	}
	
	public RentACar findOneByRentACarName(String rentACarName) {
		return rentACarRepository.findOneByRentACarName(rentACarName);
	}
	
	public RentACar findOne(Integer rentACarID)
	{
		return rentACarRepository.findOne(rentACarID);
	}
	
	public RentACar update(RentACar rent) {
		return rentACarRepository.save(rent);
	}
	
	public void delete(Integer id) {
		rentACarRepository.delete(id);
	}
	
	public Page<RentACar> findPastRentACarReservations(Integer userId, Pageable pageable) {
	
		return rentACarRepository.findPastRentACarReservations(userId, new Date(), pageable);
	}
	
	public boolean addService(MultipartFile image, String a ) {
		RentACar rnt = null;
		try {
			rnt = mapper.readValue(a, RentACarDTO.class).convert(this.destinationServices.findAll());
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		if(this.findOneByRentACarName(rnt.getRentACarName()) != null) {
			return false;
		}
		rnt = this.save(rnt);
		rnt.setImage(imageServices.saveRentACarImg(image, rnt.getRentACarID()));
		this.save(rnt);
		return true;
	}
	
	public boolean addUser(MultipartFile image, String a, Integer id) {
		UserDTO user = null;
		try {
			user = mapper.readValue(a, UserDTO.class);
		} catch (IOException e) {
			return false;
		}
		RentACar rentACar = this.findOne(id);
		RentACarAdmin admin = new RentACarAdmin();
		admin.setPassword(passwordEncoder.encode("123"));
		admin.setUsername(user.getUsername());
		admin.setFirstName(user.getFirstName());
		admin.setLastName(user.getLastName());
		admin.setEmail(user.getEmail());
		admin.setEnabled(true);
		admin.setConfirmed(false);
		admin.setRentACar(rentACar);
		rentACar.getAdmins().add(admin);
		admin.setAuthorities(Arrays.asList(authorityServices.findOne(4)));
		admin.setImage(imageServices.getUserPath(image, admin.getUsername()));
		
		rentACar = this.save(rentACar);
		
		imageServices.saveUserImg(image, admin.getUsername());
		producerServices.sendAdminRegister(admin.getEmail(), admin.getUsername(), rentACar.getRentACarName());

		return true;
	}
}
