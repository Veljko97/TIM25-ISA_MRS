package siit.tim25.rezervisi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import siit.tim25.rezervisi.DTO.TicketDTO;
import siit.tim25.rezervisi.Services.TicketServices;

@RestController
@RequestMapping(path="app/reservations")
public class ReservationController {
	
	@Autowired
	private TicketServices ticketServices;

	@GetMapping(path="/{userId}/getTickets", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TicketDTO>> getTicketsByUser(@PathVariable Integer userId) {
		return new ResponseEntity<List<TicketDTO>>(ticketServices.findTicketsByUserId(userId), HttpStatus.OK);
	}
}
