package siit.tim25.rezervisi.Beans;

public class RentACarBranch {
	
	String branchName;
	String branchAddress;
	
	
	
	public RentACarBranch() {
		super();
	}
	public RentACarBranch(String branchName, String branchAddress) {
		super();
		this.branchName = branchName;
		this.branchAddress = branchAddress;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	@Override
	public String toString() {
		return "RentACarBranch [branchName=" + branchName + ", branchAddress=" + branchAddress + "]";
	}
	
	
	
}
