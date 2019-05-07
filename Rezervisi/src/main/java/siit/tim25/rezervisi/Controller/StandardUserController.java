package siit.tim25.rezervisi.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import siit.tim25.rezervisi.Beans.users.Friends;
import siit.tim25.rezervisi.Beans.users.StandardUser;
import siit.tim25.rezervisi.DTO.FriendsDTO;
import siit.tim25.rezervisi.DTO.FriendsListsDTO;
import siit.tim25.rezervisi.DTO.StandardUserDTO;
import siit.tim25.rezervisi.Services.ProducerServices;
import siit.tim25.rezervisi.Services.users.FriendsServices;
import siit.tim25.rezervisi.Services.users.StandardUserServices;
import siit.tim25.rezervisi.security.TokenUtils;
import siit.tim25.rezervisi.security.model.TokenState;

@RestController
@RequestMapping(path="/app/users")
public class StandardUserController {

	@Autowired
	private ProducerServices producer;
	
	@Autowired
	private StandardUserServices userServices;
	
	@Autowired
	private FriendsServices friendsServices;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@RequestMapping(method = RequestMethod.POST, path = "/sendRequest/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Void> sendRequest(@PathVariable Integer userId, HttpServletRequest request) {
		StandardUser reciver = userServices.findOne(userId);
		if(reciver == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
		String token = tokenUtils.getToken(request);
		StandardUser sending = userServices.findByUsername(tokenUtils.getUsernameFromToken(token));
		if(sending.getId().equals(userId)) {
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
		Friends fRequest = friendsServices.findFriends(userId, sending.getId());
		if(fRequest != null) {
			if(fRequest.getSender().getId().equals(userId)) {
				fRequest.setConfirmed(true);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}else {
			fRequest = new Friends(sending, reciver);
		}
		Friends fr = friendsServices.save(fRequest);
		
		producer.sendRequestTo(userId.toString(), new FriendsDTO(fr.getId(), fr.getSender()));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path = "/myFriends", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FriendsListsDTO> findFrends(HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		StandardUser user = userServices.findByUsername(tokenUtils.getUsernameFromToken(token));
		return new ResponseEntity<FriendsListsDTO>(new FriendsListsDTO(user), HttpStatus.OK);
	}
	
	@PutMapping(path = "/confirmeRequest/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FriendsDTO> confirme(@PathVariable Integer requestId, HttpServletRequest request){
		Friends fr = friendsServices.findOne(requestId);
		String token = tokenUtils.getToken(request);
		StandardUser user = userServices.findByUsername(tokenUtils.getUsernameFromToken(token));
		if(!user.getId().equals(fr.getReceiver().getId())) {
			return new ResponseEntity<FriendsDTO>(HttpStatus.NOT_ACCEPTABLE);
		}
		fr.setConfirmed(true);
		friendsServices.save(fr);
		producer.sendResponseTo(fr.getSender().getId().toString(), new FriendsDTO(fr.getId(), user), true);
		return new ResponseEntity<FriendsDTO>(new FriendsDTO(requestId, fr.getSender()),HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/removeFriend/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> refuse(@PathVariable Integer requestId, HttpServletRequest request){
		Friends fr = friendsServices.findOne(requestId);
		String token = tokenUtils.getToken(request);
		StandardUser user = userServices.findByUsername(tokenUtils.getUsernameFromToken(token));
		if(!user.getId().equals(fr.getReceiver().getId())) {
			return new ResponseEntity<Integer>(HttpStatus.NOT_ACCEPTABLE);
		}
		StandardUser sender = fr.getSender();
		FriendsDTO friendDTO = new FriendsDTO(requestId, fr.getSender());
		fr.setReceiver(null);
		fr.setSender(null);
		friendsServices.delete(fr);
		producer.sendResponseTo(sender.getId().toString(), friendDTO, false);
		return new ResponseEntity<Integer>(requestId, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/deleteFriend/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> delete(@PathVariable Integer requestId, HttpServletRequest request){
		Friends fr = friendsServices.findOne(requestId);
		String token = tokenUtils.getToken(request);
		StandardUser user = userServices.findByUsername(tokenUtils.getUsernameFromToken(token));
		StandardUser other = null;
		if(user.getId().equals(fr.getReceiver().getId())){
			other = fr.getSender();
		}
		if(user.getId().equals(fr.getSender().getId())) {
			other = fr.getReceiver();
		}
		if(other == null) {
			return new ResponseEntity<Integer>(HttpStatus.NOT_ACCEPTABLE);
		}
		FriendsDTO friendDTO = new FriendsDTO(requestId, user);
		fr.setReceiver(null);
		fr.setSender(null);
		friendsServices.delete(fr);
		producer.sendDeleteTo(other.getId().toString(), friendDTO);
		return new ResponseEntity<Integer>(requestId, HttpStatus.OK);
	}
	
	@GetMapping(path = "/findUser", params = "serchParam", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<StandardUserDTO>> findUsers(@RequestParam("serchParam") String serchParam, Pageable pageable, HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		Integer myId;
		if(token != null) {
			StandardUser user = userServices.findByUsername(tokenUtils.getUsernameFromToken(token));
			myId = user.getId();
		}else {
			myId = -1;
		}
		
		Page<StandardUser> page = userServices.findMatch(serchParam, myId, pageable);
		List<StandardUserDTO> listDTO = new ArrayList<StandardUserDTO>();
		for(StandardUser userr : page.getContent()) {
			listDTO.add(new StandardUserDTO(userr, new TokenState()));
		}
		return new ResponseEntity<Page<StandardUserDTO>>(new PageImpl<StandardUserDTO>(listDTO,pageable ,page.getTotalElements()), HttpStatus.OK);
	}
	
}
