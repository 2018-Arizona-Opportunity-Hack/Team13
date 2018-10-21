package models;

import java.util.ArrayList;
import java.util.List;

public class MonthlyAggregateResponse {

	public MonthlyAggregateResponse(String month, List<CategoryAggregate> aggregate) {
		this.month = month;
		this.aggregate.addAll(aggregate);
	}

	private static final long serialVersionUID = 2462415672938735170L;
	private String month;
	List<CategoryAggregate> aggregate = new ArrayList<>();

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List<CategoryAggregate> getAggregate() {
		return aggregate;
	}

	public void setAggregate(List<CategoryAggregate> aggregate) {
		this.aggregate = aggregate;
	}

	public void addAggregateElements(List<CategoryAggregate> cat) {
		this.aggregate.addAll(cat);
	}

	@Override
	public String toString() {
		return "MonthlyAggregateResponse [month=" + month + ", aggregate=" + aggregate + "]";
	}

}
