package siit.tim25.rezervisi.Services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.Beans.Destination;
import siit.tim25.rezervisi.Repository.AirLineRepository;
import siit.tim25.rezervisi.Repository.DestinationRepository;

@Component
public class DestinationServices {
	@Autowired
	private AirLineRepository airLineRepository;
	
	@Autowired
	private DestinationRepository destinationRepository;
	
	public Destination save(Integer airLineId, Destination d) {
		AirLine a = airLineRepository.findOne(airLineId);
		d.setAirLine(a);
		a.getAirLineDestinations().add(d);
		airLineRepository.save(a);
		return d;
	}
	
	public Set<Destination> findAll(Integer airLineId){
		AirLine a = airLineRepository.findOne(airLineId);

		return a.getAirLineDestinations();
	}
	
	public Destination findOne(Integer airLineId, Integer destinationId)
	{
		AirLine a = airLineRepository.findOne(airLineId);
		Destination d = null;
		for(Destination ad: a.getAirLineDestinations()) {
			if (ad.getIdDestination() == destinationId) {
				d = ad;
			}
		}
		return d;
	}
	
	public Destination update(Integer airlineId, Destination d) {
		AirLine a = airLineRepository.findOne(airlineId);
		Destination dest = null;
		for(Destination ad: a.getAirLineDestinations()) {
			if (ad.getIdDestination() == d.getIdDestination()) {
				ad.setDestinationName(d.getDestinationName());
				ad.setDestinationDescription(d.getDestinationDescription());
			}
			
		}
		airLineRepository.save(a);
		return dest;
	}
	
	public void delete(Integer airlineId, Integer destinationId) {
		AirLine a = airLineRepository.findOne(airlineId);
		Destination adest = null;
		for(Destination ad: a.getAirLineDestinations()) {
			if (ad.getIdDestination() == destinationId) {
				adest = ad;
			}
		}
		adest.setAirLine(null);
		a.getAirLineDestinations().remove(adest);
		airLineRepository.save(a);
		destinationRepository.delete(adest);
	}
}
