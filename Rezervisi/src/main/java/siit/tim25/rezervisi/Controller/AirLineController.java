package siit.tim25.rezervisi.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

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
	public ArrayList<AirLine> addAirline(@RequestBody AirLine airline)  {
		airline.setAirLineAverageGrade(0.0);
		airline.setAirlineEarning(0.0);
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
		
		return airlineList;
	}
	
	@GetMapping(path="/showAirLines", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<AirLine> showAirLines()
	{
		ArrayList<AirLine> airlaneList = airLineServices.getAirLineList();
		return airlaneList;
	}
	
	@GetMapping(path="/getAirline", produces = MediaType.APPLICATION_JSON_VALUE)
	public AirLine getAirline(HttpServletRequest request)
	{
		return airLineServices.getAirLine(Integer.parseInt(request.getParameter("index")));
	}
	
	@PostMapping(path="/editAirline", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void editAirline(HttpServletRequest request, @RequestBody AirLine modifiedAirline)
	{
		ArrayList<AirLine> airlineList = airLineServices.getAirLineList();
		AirLine airline = null;
		Integer airlineID = Integer.parseInt(request.getParameter("id"));
		
		for(AirLine a: airlineList) {
			if (a.getAirLineID().equals(airlineID)) {
				airline = a;
			}
		}
		
		if (airline != null) {
			airline.setAirLineName(modifiedAirline.getAirLineName());
			airline.setAirLineAddress(modifiedAirline.getAirLineAddress());
			airline.setAirLineDescription(modifiedAirline.getAirLineDescription());
		}
	}
	
}
