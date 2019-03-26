package siit.tim25.rezervisi.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import siit.tim25.rezervisi.Beans.RentACar;
import siit.tim25.rezervisi.Repository.RentACarRepository;


@Component
public class RentACarServices {
	@Autowired
	private RentACarRepository rentACarRepository;
	
	public boolean save(RentACar rnt) {
		return rentACarRepository.save(rnt);
	}
	
	public ArrayList<RentACar> getRentACarList(){
		return rentACarRepository.getRentACarList();
	}
	
	public RentACar getRentACar(Integer rentACarID)
	{
		return rentACarRepository.getRentACar(rentACarID);
	}
	
	public boolean update(RentACar rent) {
		return rentACarRepository.update(rent);
	}
	
}
