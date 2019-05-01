package siit.tim25.rezervisi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.DTO.FriendsDTO;

@Component
public class ProducerServices {
	@Autowired
	private SimpMessagingTemplate template;

	public void sendRequestTo(String sentTo, FriendsDTO frends) {
		this.template.convertAndSend("/request/"+sentTo, frends);
	}
	
	public void sendResponseTo(String sentTo, FriendsDTO request, Boolean isConfirmed) {
		RequestResponse res = new RequestResponse();
		res.friendRequest = request;
		res.isConfirmed = isConfirmed;
		this.template.convertAndSend("/response/"+sentTo, res);
	}
	
	public void sendDeleteTo(String sentTo, FriendsDTO friends) {
		this.template.convertAndSend("/deleted/"+sentTo, friends);
	}
	
	class RequestResponse {
		public FriendsDTO friendRequest;
		public Boolean isConfirmed;
	}
}