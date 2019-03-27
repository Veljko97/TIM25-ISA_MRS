package siit.tim25.rezervisi.Beans;

public class Destination {
	
	String destinationName;
	String destinationDescription;

	public Destination() {
		super();
	}
	
	public Destination (String destinationName) {
		super();
		this.destinationName = destinationName;
	}
	
	public Destination(String destinationName, String destinationDescription) {
		super();
		this.destinationName = destinationName;
		this.destinationDescription = destinationDescription;
	}
	
	public String getDestinationName() {
		return destinationName;
	}
	
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public String getDestinationDescription() {
		return destinationDescription;
	}
	public void setDestinationDescription(String destinationDescription) {
		this.destinationDescription = destinationDescription;
	}

	@Override
	public String toString() {
		return "Destination [destinationName=" + destinationName + ", destinationDescription=" + destinationDescription
				+ "]";
	}
	
	
	
	
}
