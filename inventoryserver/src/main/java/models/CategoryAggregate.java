package models;

import org.apache.commons.lang3.StringUtils;

public class CategoryAggregate {
	private String category;
	private Integer totalPounds;
	private Integer totalDollarVal;

	public void addUp(String category, String totalPounds, String totalDollarVal) {
		this.category = category;
		this.totalDollarVal += (StringUtils.isBlank(totalDollarVal) ? 0 : Integer.valueOf(totalDollarVal));
		this.totalPounds += (StringUtils.isBlank(totalPounds) ? 0 : Integer.valueOf(totalPounds));
	}

	public CategoryAggregate(String category, Integer totalPounds, Integer totalDollarVal) {
		super();
		this.category = category;
		this.totalPounds = totalPounds;
		this.totalDollarVal = totalDollarVal;
	}

	public CategoryAggregate() {
		totalPounds = 0;
		totalDollarVal = 0;
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

	public Integer getTotalPounds() {
		return totalPounds;
	}

	public void setTotalPounds(Integer totalPounds) {
		this.totalPounds = totalPounds;
	}

	public Integer getTotalDollarVal() {
		return totalDollarVal;
	}

	public void setTotalDollarVal(Integer totalDollarVal) {
		this.totalDollarVal = totalDollarVal;
	}

}
