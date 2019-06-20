package siit.tim25.rezervisi.Beans;

public enum FlightType {
	ROUND_TRIP(0), ONE_WAY(1), MULTI_CITY(2);
	
	private final int value;
    private FlightType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
