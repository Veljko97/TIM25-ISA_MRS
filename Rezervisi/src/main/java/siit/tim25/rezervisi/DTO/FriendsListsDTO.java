package siit.tim25.rezervisi.DTO;

import java.util.ArrayList;

import siit.tim25.rezervisi.Beans.users.Friends;
import siit.tim25.rezervisi.Beans.users.StandardUser;

public class FriendsListsDTO {

	private ArrayList<FriendsDTO> accepted = new ArrayList<FriendsDTO>();
	private ArrayList<FriendsDTO> pending = new ArrayList<FriendsDTO>();
	private ArrayList<FriendsDTO> confirmation = new ArrayList<FriendsDTO>();
	
	public FriendsListsDTO (StandardUser user) {
		for (Friends fr : user.getSentRequest()) {
			if(fr.getConfirmed()) {
				accepted.add(new FriendsDTO(fr.getId(), fr.getReceiver()));
			}else {
				pending.add(new FriendsDTO(fr.getId(), fr.getReceiver()));
			}
		}
		for (Friends fr : user.getReceivedRequests()) {
			if(fr.getConfirmed()) {
				accepted.add(new FriendsDTO(fr.getId(), fr.getSender()));
			}else {
				confirmation.add(new FriendsDTO(fr.getId(), fr.getSender()));
			}
		}
	}

	public ArrayList<FriendsDTO> getAccepted() {
		return accepted;
	}

	public void setAccepted(ArrayList<FriendsDTO> accepted) {
		this.accepted = accepted;
	}

	public ArrayList<FriendsDTO> getPending() {
		return pending;
	}

	public void setPending(ArrayList<FriendsDTO> pending) {
		this.pending = pending;
	}

	public ArrayList<FriendsDTO> getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(ArrayList<FriendsDTO> confirmation) {
		this.confirmation = confirmation;
	}
}
