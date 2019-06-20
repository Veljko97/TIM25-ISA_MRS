package siit.tim25.rezervisi.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DiscountPoint {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private Integer pointsNeeded;
	
	@Column
	private Double discountPercent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPointsNeeded() {
		return pointsNeeded;
	}

	public void setPointsNeeded(Integer pointsNeeded) {
		this.pointsNeeded = pointsNeeded;
	}

	public Double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}
}
