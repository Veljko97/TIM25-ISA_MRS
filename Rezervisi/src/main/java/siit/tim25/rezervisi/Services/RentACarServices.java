package siit.tim25.rezervisi.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.RentACar;
import siit.tim25.rezervisi.Repository.RentACarRepository;


@Component
public class RentACarServices {
	@Autowired
	private RentACarRepository rentACarRepository;
	
	public RentACar save(RentACar rnt) {
		return rentACarRepository.save(rnt);
	}
	
	public List<RentACar> findAll(){
		return rentACarRepository.findAll();
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
	
}
