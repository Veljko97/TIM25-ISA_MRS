package siit.tim25.rezervisi.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//Spring Framework
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import siit.tim25.rezervisi.Beans.ReportDateSearch;
import siit.tim25.rezervisi.DTO.TicketReportDTO;
import siit.tim25.rezervisi.Services.TicketServices;
@Controller
@RequestMapping("/app/report")
public class ReportController {

	@Autowired
	private TicketServices ticketServices;
	
	@RequestMapping(value = "/{airlineId}/dailyTicket", method = RequestMethod.GET)
	public @ResponseBody List<TicketReportDTO> getDailyTicketReport(@PathVariable Integer airlineId) {
		return ticketServices.getDailyReport(airlineId);
	}
	
	@RequestMapping(value = "/{airlineId}/weeklyTicket", method = RequestMethod.GET)
	public @ResponseBody List<TicketReportDTO> getWeeklyTicketReport(@PathVariable Integer airlineId) {
		return ticketServices.getWeeklyReport(airlineId);
	}
	
	@RequestMapping(value = "/{airlineId}/monthlyTicket", method = RequestMethod.GET)
	public @ResponseBody List<TicketReportDTO> getMonthlyTicketReport(@PathVariable Integer airlineId) {
		return ticketServices.getMonthlyReport(airlineId);
	}
	
	@RequestMapping(value = "/{airlineId}/ticketDates", method = RequestMethod.POST)
	public ResponseEntity<Double> getTicketDatesReport(@PathVariable Integer airlineId,@RequestBody ReportDateSearch r) {
		Double result = ticketServices.getDatesReport(airlineId, new Date(r.getStartDate()), new Date(r.getEndDate()));
		result = result == null ? 0 : result;
		return new ResponseEntity<Double>(result, HttpStatus.OK);
	}
	
	
}