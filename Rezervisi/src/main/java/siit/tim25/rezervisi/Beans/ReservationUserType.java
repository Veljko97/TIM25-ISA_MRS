package siit.tim25.rezervisi.Beans;

public enum ReservationUserType {
	CURRENT(0), REGISTERED(1), UNREGISTERED(2);
	
	private final int value;
	private ReservationUserType(int value) {
	    this.value = value;
	}
	
	public int getValue() {
	    return value;
	}
}
