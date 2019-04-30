package siit.tim25.rezervisi.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
}
