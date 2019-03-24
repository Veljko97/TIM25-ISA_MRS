package siit.tim25.rezervisi.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.Services.AirLineServices;

@RestController
@RequestMapping(path="app/airlines")
public class AirLineController {
	
	@Autowired
	private AirLineServices airLineServices;
	
	@PostMapping(path="/addAirline", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addAirline(@RequestBody AirLine airline)  {
		
		ArrayList<AirLine> airlineList = airLineServices.getAirLineList();
		boolean exists = false;
		for(AirLine existingAirline : airlineList)
		{
			if(existingAirline.getAirLineID().equals(airline.getAirLineID()))
			{
				exists = true;
				break;
			}
		}
		if(exists == false)
		{
			airLineServices.save(airline);
		}
	}
	
	@GetMapping(path="/showAirLines", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<AirLine> showAirLines()
	{
		ArrayList<AirLine> airlaneList = airLineServices.getAirLineList();
		return airlaneList;
	}
	
}
