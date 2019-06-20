package siit.tim25.rezervisi.DTO;

import java.util.Arrays;

public class ReservationIdsDTO {
	private Integer ticketId;
	private Integer[] vehicleIds;
	private Integer[] roomIds;
	private Boolean usePoints;
	
	public ReservationIdsDTO() {
		
	}

	public ReservationIdsDTO(Integer ticketId, Integer[] vehicleIds, Integer[] roomIds, Boolean usePoints) {
		super();
		this.ticketId = ticketId;
		this.vehicleIds = vehicleIds;
		this.roomIds = roomIds;
		this.usePoints = usePoints;
	}

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public Integer[] getVehicleIds() {
		return vehicleIds;
	}

	public void setVehicleIds(Integer[] vehicleIds) {
		this.vehicleIds = vehicleIds;
	}

	public Integer[] getRoomIds() {
		return roomIds;
	}

	public void setRoomIds(Integer[] roomIds) {
		this.roomIds = roomIds;
	}
	
	public Boolean getUsePoints() {
		return usePoints;
	}

	public void setUsePoints(Boolean usePoints) {
		this.usePoints = usePoints;
	}

	@Override
	public String toString() {
		return "ReservationIdsDTO [ticketId=" + ticketId + ", vehicleIds=" + Arrays.toString(vehicleIds) + ", roomIds="
				+ Arrays.toString(roomIds) + "]";
	}
	
	
	
}
