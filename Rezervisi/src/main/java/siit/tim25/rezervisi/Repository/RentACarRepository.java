package siit.tim25.rezervisi.Repository;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.RentACar;


@Component
public class RentACarRepository {

	private ArrayList<RentACar> rentACars = new ArrayList<RentACar>();
	
	public boolean save(RentACar rnt) {
		rnt.setRentACarID(rentACars.size());;
		rentACars.add(rnt);
		return true;
	}
	
	public ArrayList<RentACar> getRentACarList(){
		return rentACars;
	}
	
	public RentACar getRentACar(Integer rentACarID)
	{
		return rentACars.get(rentACarID);
	}

	public boolean update(RentACar rentAcar) {
		for(int i = 0; i < rentACars.size(); i++) {
			if (rentACars.get(i).getRentACarID().equals(rentAcar.getRentACarID())) {
				rentACars.set(i, rentAcar);
				return true;
			}
		}
		return false;
	}
}
