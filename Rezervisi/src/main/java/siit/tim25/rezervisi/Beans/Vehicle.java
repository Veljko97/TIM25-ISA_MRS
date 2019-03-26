package siit.tim25.rezervisi.Beans;

public class Vehicle {
	
	private Integer id;
	private String vehicleName;
	private String vehicleGrade;
	
	public Vehicle() {
		this.id = -1;
		this.vehicleName = "";
		this.vehicleGrade = "";
	}
	
	public Vehicle(Integer id, String vehicleName, String vehicleGrade) {
		super();
		this.id = id;
		this.vehicleName = vehicleName;
		this.vehicleGrade = vehicleGrade;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getVehicleGrade() {
		return vehicleGrade;
	}

	public void setVehicleGrade(String vehicleGrade) {
		this.vehicleGrade = vehicleGrade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
}
