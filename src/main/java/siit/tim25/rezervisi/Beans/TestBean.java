package siit.tim25.rezervisi.Beans;

public class TestBean {
	private Integer id;
	private String message;
	
	public TestBean() {
		this.id = -1;
		this.message = "";
	}
	
	public TestBean(Integer id, String message) {
		super();
		this.id = id;
		this.message = message;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
