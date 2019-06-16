package siit.tim25.rezervisi.Services;


import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import siit.tim25.rezervisi.Beans.Hotel;
import siit.tim25.rezervisi.Beans.users.HotelAdmin;
import siit.tim25.rezervisi.DTO.HotelDTO;
import siit.tim25.rezervisi.DTO.UserDTO;
import siit.tim25.rezervisi.Repository.HotelRepository;
import siit.tim25.rezervisi.Services.users.AuthorityServices;


@Component
public class HotelServices {
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private DestinationServices destinationServices;

	@Autowired
	private AuthorityServices authorityServices;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private ImageServices imageServices;
	
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	
	public Hotel save(Hotel hotel) {
		return hotelRepository.save(hotel);
	}
	
	public List<Hotel> findAll(){
		return hotelRepository.findAll();
	}
	
	public Page<HotelDTO> findAllAndConvert(Pageable pageable) {
		Page<Hotel> hotelList = hotelRepository.findAll(pageable);
		return hotelList.map(new Converter<Hotel, HotelDTO>() {
		    @Override
		    public HotelDTO convert(Hotel entity) {
		        return entity.convert();
		    }
		});
	}
	
	public Page<Hotel> findAll(Pageable pageable){
		return hotelRepository.findAll(pageable);
	}
	
	public Page<HotelDTO> search(String name, String destination, Pageable pageable) {
		Page<Hotel> hotelList = hotelRepository.find(name, destination, pageable);
		return hotelList.map(new Converter<Hotel, HotelDTO>() {
		    @Override
		    public HotelDTO convert(Hotel entity) {
		        return entity.convert();
		    }
		});
	}
	public Hotel findOne(Integer hotelID)
	{
		return hotelRepository.findOne(hotelID);
	}
	
	public void delete(Integer id) {
		hotelRepository.delete(id);
	}
	
	public Page<HotelDTO> findPastHotelReservations(Integer userId, Pageable pageable) {
		Page<Hotel> ho = hotelRepository.findPastHotelReservations(userId, new Date(), pageable);
		return ho.map(new Converter<Hotel, HotelDTO>(){

			@Override
			public HotelDTO convert(Hotel source) {
				return new HotelDTO(source);
			}});
	}
	
	public boolean addHotel(MultipartFile image, String a ) {
		HotelDTO hotelDTO = null;
		Hotel hotel = null;
		try {
			hotelDTO = mapper.readValue(a, HotelDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			hotel = this.save(hotelDTO.convert(destinationServices.findAll()));
		} catch (ParseException e) {
			return false;
		}
		hotel.setImage(imageServices.saveHotelImg(image, hotel.getHotelID()));
		this.save(hotel);
		return true;
	}
	
	public boolean addUser(MultipartFile image, String a, Integer id)
	{
		UserDTO user = null;
		try {
			user = mapper.readValue(a, UserDTO.class);
		} catch (IOException e) {
			;
		}
		
		Hotel hotel = this.findOne(id);
		HotelAdmin admin = new HotelAdmin();
		admin.setPassword(passwordEncoder.encode("123"));
		admin.setUsername(user.getUsername());
		admin.setFirstName(user.getFirstName());
		admin.setLastName(user.getLastName());
		admin.setEmail(user.getEmail());
		admin.setEnabled(true);
		admin.setConfirmed(false);
		admin.setHotel(hotel);
		hotel.getAdmins().add(admin);
		admin.setAuthorities(Arrays.asList(authorityServices.findOne(2)));		
		admin.setImage(imageServices.getUserPath(image, admin.getUsername()));

		hotel = this.save(hotel);
		
		imageServices.saveUserImg(image, admin.getUsername());
		return true;
	}
	
	
}
