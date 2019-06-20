package siit.tim25.rezervisi.Repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import siit.tim25.rezervisi.Beans.users.Friends;

public interface FriendsRepository extends JpaRepository<Friends, Integer> {
	
	@Query("SELECT fr FROM Friends fr WHERE (fr.sender.id = :userId1 and fr.receiver.id = :userId2) or (fr.sender.id = :userId2 and fr.receiver.id = :userId1)")
	public Friends findFriends(@Param("userId1") Integer userId1, @Param("userId2") Integer userId2);
}
