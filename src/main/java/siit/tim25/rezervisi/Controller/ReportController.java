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
import siit.tim25.rezervisi.DTO.RoomReportDTO;
import siit.tim25.rezervisi.DTO.TicketReportDTO;
import siit.tim25.rezervisi.DTO.VehicleReportDTO;
import siit.tim25.rezervisi.Services.RoomReservationServices;
import siit.tim25.rezervisi.Services.TicketServices;
import siit.tim25.rezervisi.Services.VehicleReservationServices;
@Controller
@RequestMapping("/app/report")
public class ReportController {

	@Autowired
	private TicketServices ticketServices;
	
	@Autowired
	private RoomReservationServices roomReservationServices;
	
	@Autowired
	private VehicleReservationServices vehicleReservationServices;
	
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
	
	@RequestMapping(value = "/{serviceId}/dailyVehicle", method = RequestMethod.GET)
	public @ResponseBody List<VehicleReportDTO> getDailyVehicleReport(@PathVariable Integer serviceId) {
		return vehicleReservationServices.getDailyReport(serviceId);
	}
	
	@RequestMapping(value = "/{serviceId}/weeklyVehicle", method = RequestMethod.GET)
	public @ResponseBody List<VehicleReportDTO> getWeeklyVehicleReport(@PathVariable Integer serviceId) {
		return vehicleReservationServices.getWeeklyReport(serviceId);
	}
	
	@RequestMapping(value = "/{serviceId}/monthlyVehicle", method = RequestMethod.GET)
	public @ResponseBody List<VehicleReportDTO> getMonthlyVehicleReport(@PathVariable Integer serviceId) {
		return vehicleReservationServices.getMonthlyReport(serviceId);
	}
	
	@RequestMapping(value = "/{serviceId}/vehicleDates", method = RequestMethod.POST)
	public ResponseEntity<Double> getVehicleDatesReport(@PathVariable Integer serviceId,@RequestBody ReportDateSearch r) {
		Double result = vehicleReservationServices.getDatesReport(serviceId, new Date(r.getStartDate()), new Date(r.getEndDate()));
		result = result == null ? 0 : result;
		return new ResponseEntity<Double>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{hotelId}/dailyRooms", method = RequestMethod.GET)
	public @ResponseBody List<RoomReportDTO> getDailyRoomReport(@PathVariable Integer hotelId) {
		return roomReservationServices.getDailyReport(hotelId);
	}
	
	@RequestMapping(value = "/{hotelId}/weeklyRooms", method = RequestMethod.GET)
	public @ResponseBody List<RoomReportDTO> getWeeklyRoomReport(@PathVariable Integer hotelId) {
		return roomReservationServices.getWeeklyReport(hotelId);
	}
	
	@RequestMapping(value = "/{hotelId}/monthlyRooms", method = RequestMethod.GET)
	public @ResponseBody List<RoomReportDTO> getMonthlyRoomReport(@PathVariable Integer hotelId) {
		return roomReservationServices.getMonthlyReport(hotelId);
	}
	
	@RequestMapping(value = "/{hotelId}/roomDates", method = RequestMethod.POST)
	public ResponseEntity<Double> getRoomDatesReport(@PathVariable Integer hotelId,@RequestBody ReportDateSearch r) {
		Double result = roomReservationServices.getDatesReport(hotelId, new Date(r.getStartDate()), new Date(r.getEndDate()));
		result = result == null ? 0 : result;
		return new ResponseEntity<Double>(result, HttpStatus.OK);
	}
	
	
	
	
}