package siit.tim25.rezervisi.Services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siit.tim25.rezervisi.Beans.users.Friends;
import siit.tim25.rezervisi.Repository.users.FriendsRepository;

@Service
public class FriendsServices {
	
	@Autowired
	private FriendsRepository friendsRepository;
	
	public Friends save(Friends friends) {
		return friendsRepository.save(friends);
	}
	
	public Friends findFriends(Integer userId1, Integer userId2) {
		return friendsRepository.findFriends(userId1, userId2);
	}
	
	public Friends findOne(Integer id) {
		return friendsRepository.findOne(id);
	}
	
	public void delete(Friends friends) {
		friendsRepository.delete(friends);
	}
}
