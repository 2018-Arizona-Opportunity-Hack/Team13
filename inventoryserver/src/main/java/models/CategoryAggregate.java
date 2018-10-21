package models;

public class CategoryAggregate {

	private String category;
	private Integer totalPounds;
	private Integer totalDollarVal;

	
	public void addUp(String category, Integer totalPounds, Integer totalDollarVal) {
		this.category = category;
		this.totalDollarVal+=(totalDollarVal==null? 0 : totalDollarVal);
		this.totalPounds+=(totalPounds == null ? 0 : totalPounds);
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
