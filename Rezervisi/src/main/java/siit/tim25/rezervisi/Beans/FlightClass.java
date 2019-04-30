package siit.tim25.rezervisi.Beans;

public enum FlightClass {
	BUSINESS(0), ECONOMY(1), FIRST(2);
	
	private final int value;
    private FlightClass(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
