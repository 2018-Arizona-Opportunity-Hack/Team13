package models;

import java.util.ArrayList;
import java.util.List;

public class YearlyResponse extends Response {

	public YearlyResponse(int code, String msg) {
		super(code, msg);
	}

	List<MonthlyAggregateResponse> resultList = new ArrayList<>();
	
	
	
	public List<MonthlyAggregateResponse> getResultList() {
		return resultList;
	}



	public void setResultList(List<MonthlyAggregateResponse> resultList) {
		this.resultList.addAll(resultList);
	}

	private static final long serialVersionUID = -8682641583908906169L;

	
}
