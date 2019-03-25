package siit.tim25.rezervisi.Repository;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.AirLine;

@Component
public class AirLineRepository {

	private ArrayList<AirLine> airlines = new ArrayList<AirLine>();
	
	public boolean save(AirLine airline) {
		airline.setAirLineID(airlines.size());
		airlines.add(airline);
		return true;
	}
	
	public ArrayList<AirLine> getAirlineList(){
		return airlines;
	}
	
	public AirLine getAirLine(Integer airLineID)
	{
		return airlines.get(airLineID);
	}
	
	
}
