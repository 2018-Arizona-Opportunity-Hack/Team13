package models;

import java.util.List;

public class AggregateResponse extends Response {

	public AggregateResponse(int code, String msg, List<CategoryAggregate> aggregate) {
		super(code, msg);
		this.aggregate=aggregate;
	}

	public List<CategoryAggregate> getAggregate() {
		return aggregate;
	}

	public void setAggregate(List<CategoryAggregate> aggregate) {
		this.aggregate = aggregate;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7544705489718563388L;

	private List<CategoryAggregate> aggregate ;
	
	
}
