package models;

import org.apache.commons.lang3.StringUtils;

public class CategoryAggregate {
	private String category;
	private Double totalPounds;
	private Double totalDollarVal;

	public void addUp(String category, String totalPounds, String totalDollarVal) {
		this.category = category;
		this.totalDollarVal += (StringUtils.isBlank(totalDollarVal) ? 0 : Double.valueOf(totalDollarVal));
		this.totalPounds += (StringUtils.isBlank(totalPounds) ? 0 : Double.valueOf(totalPounds));
	}

	public CategoryAggregate(String category, Double totalPounds, Double totalDollarVal) {
		super();
		this.category = category;
		this.totalPounds = totalPounds;
		this.totalDollarVal = totalDollarVal;
	}

	public CategoryAggregate() {
		totalPounds = 0.0;
		totalDollarVal = 0.0;
	}

	@Override
	public String toString() {
		return "CategoryAggregate [category=" + category + ", totalPounds=" + totalPounds + ", totalDollarVal="
				+ totalDollarVal + "]";
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getTotalPounds() {
		return totalPounds;
	}

	public void setTotalPounds(Double totalPounds) {
		this.totalPounds = totalPounds;
	}

	public Double getTotalDollarVal() {
		return totalDollarVal;
	}

	public void setTotalDollarVal(Double totalDollarVal) {
		this.totalDollarVal = totalDollarVal;
	}

}
