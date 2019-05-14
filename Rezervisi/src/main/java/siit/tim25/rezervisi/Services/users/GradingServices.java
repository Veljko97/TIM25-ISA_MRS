package siit.tim25.rezervisi.Services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siit.tim25.rezervisi.Beans.AirLine;
import siit.tim25.rezervisi.Beans.Flight;
import siit.tim25.rezervisi.Beans.Hotel;
import siit.tim25.rezervisi.Beans.RentACar;
import siit.tim25.rezervisi.Beans.Room;
import siit.tim25.rezervisi.Beans.Vehicle;
import siit.tim25.rezervisi.Beans.Grades.AirLineGrade;
import siit.tim25.rezervisi.Beans.Grades.FlightGrade;
import siit.tim25.rezervisi.Beans.Grades.HotelGrade;
import siit.tim25.rezervisi.Beans.Grades.RentACarGrade;
import siit.tim25.rezervisi.Beans.Grades.RoomGrade;
import siit.tim25.rezervisi.Beans.Grades.VehicleGrade;
import siit.tim25.rezervisi.Repository.AirLineRepository;
import siit.tim25.rezervisi.Repository.FlightRepository;
import siit.tim25.rezervisi.Repository.HotelRepository;
import siit.tim25.rezervisi.Repository.RentACarRepository;
import siit.tim25.rezervisi.Repository.RoomRepository;
import siit.tim25.rezervisi.Repository.VehicleRepository;

@Service
public class GradingServices {
	
	@Autowired
	private AirLineRepository airLineRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private RentACarRepository rentACarRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	private void updateAirLineGrade(AirLine service) {
		double sum = 0.0;
		for(AirLineGrade grade : service.getGrades()) {
			sum += grade.getScore();
		}
		service.setAverageGrade(sum / service.getGrades().size());
		airLineRepository.save(service);
	}
	
	private void updateHotelGrade(Hotel service) {
		double sum = 0.0;
		for(HotelGrade grade : service.getGrades()) {
			sum += grade.getScore();
		}
		service.setAverageGrade(sum / service.getGrades().size());
		hotelRepository.save(service);
	}
	
	private void updateRentACarGrade(RentACar service) {
		double sum = 0.0;
		for(RentACarGrade grade : service.getGrades()) {
			sum += grade.getScore();
		}
		service.setAverageGrade(sum / service.getGrades().size());
		rentACarRepository.save(service);
	}
	
	private void updateRoomGrade(Room service) {
		double sum = 0.0;
		for(RoomGrade grade : service.getGrades()) {
			sum += grade.getScore();
		}
		service.setAverageGrade(sum / service.getGrades().size());
		roomRepository.save(service);
	}
	
	private void updateVehicleGrade(Vehicle service) {
		double sum = 0.0;
		for(VehicleGrade grade : service.getGrades()) {
			sum += grade.getScore();
		}
		service.setAverageGrade(sum / service.getGrades().size());
		vehicleRepository.save(service);
	}
	
	private void updateFlightGrade(Flight service) {
		double sum = 0.0;
		for(FlightGrade grade : service.getGrades()) {
			sum += grade.getScore();
		}
		service.setAverageGrade(sum / service.getGrades().size());
		flightRepository.save(service);
	}
	
	public void updateGrade(Object service, String type) {
		switch (type) {
		case "AirLine":
			this.updateAirLineGrade((AirLine)service);
			break;
		case "Hotel":
			this.updateHotelGrade((Hotel)service);
			break;
		case "RentACar":
			this.updateRentACarGrade((RentACar)service);
			break;
		case "Room":
			this.updateRoomGrade((Room)service);
			break;
		case "Vehicle":
			this.updateVehicleGrade((Vehicle)service);
			break;
		case "Flight":
			this.updateFlightGrade((Flight)service);
			break;
		default:
			break;
		}
	}
}
