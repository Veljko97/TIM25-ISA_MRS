package siit.tim25.rezervisi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import siit.tim25.rezervisi.DTO.FriendsDTO;

@Component
public class ProducerServices {
	@Autowired
	private SimpMessagingTemplate template;
	
	@Autowired
	private JavaMailSender mailSender;


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
	
	@Async
	public void sendEmailTo(String subject, String sendTo, String text) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(sendTo);
		mail.setSubject(subject);
		
		mail.setText(text);
		mailSender.send(mail);
	}
	
	@Async
	public void sendFriendRequestTo(String sendTo, String sending) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(sendTo);
		mail.setSubject("Friend Request on Reservify");
		
		mail.setText("You have friend invite on Reservify service from " + sending + "\n Have Fun");
		mailSender.send(mail);
	}
	
	@Async
	public void sendAdminRegister(String sendTo, String username, String serviceName) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(sendTo);
		mail.setSubject("Registerd as Admin for "+ serviceName +" on Reservify");
		
		mail.setText("You have benn registerd as admin on Reservify for " + serviceName
				+ "\n your Log in info is \n User Name: " + username + "\n Password: 123 \n"
				+ "You will need to change passwor first time you log in");
		mailSender.send(mail);
	}
	
	@Async
	public void sendUserRegister(String sendTo, String username, String host, String confirmToken) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(sendTo);
		mail.setSubject("Account confirmation for your Reservify account");
		
		
		mail.setText("Hi"+ username +", \nYou recive this mail becuse you have tried to register on Reservify.\n "
				+"To confirm you Account go to: \n" + host  + "/auth/confirmAccount?token=" + confirmToken);
		mailSender.send(mail);
	}
	
	class RequestResponse {
		public FriendsDTO friendRequest;
		public Boolean isConfirmed;
	}
}
