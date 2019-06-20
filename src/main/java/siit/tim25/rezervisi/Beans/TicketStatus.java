package siit.tim25.rezervisi.Beans;

public enum TicketStatus {
	ACCEPTED(0), PENDING(1), FAST(3);
	
	private final int value;
    private TicketStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
