package siit.tim25.rezervisi.Beans;

public class ReportDateSearch {
	private Long startDate;
	private Long endDate;
	
	public ReportDateSearch() {
		
	}

	public ReportDateSearch(Long startDate, Long endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}



	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "ReportDateSearch [startDate=" + startDate + ", endDate=" + endDate + ", getStartDate()="
				+ getStartDate() + ", getEndDate()=" + getEndDate();
	}

}
