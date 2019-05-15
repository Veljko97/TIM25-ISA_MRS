package siit.tim25.rezervisi.Beans;

public enum InvitationResponseType {
	ACCEPTED(0), DECLINED(1);
	
	private final int value;
    private InvitationResponseType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
