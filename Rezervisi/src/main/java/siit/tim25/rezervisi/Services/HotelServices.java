package siit.tim25.rezervisi.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.Hotel;
import siit.tim25.rezervisi.DTO.HotelDTO;
import siit.tim25.rezervisi.Repository.HotelRepository;


@Component
public class HotelServices {
	
	@Autowired
	private HotelRepository hotelRepository;
	
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
}
