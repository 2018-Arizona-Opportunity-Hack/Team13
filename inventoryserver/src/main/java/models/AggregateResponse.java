package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AggregateResponse extends Response {

	public AggregateResponse(int code, String msg, List<CategoryAggregate> aggregate) {
		super(code, msg);
	}

	private static final long serialVersionUID = -7544705489718563388L;

	//map of month, list of aggregates based on category
	private  Map<String, List<CategoryAggregate>> aggregate ;


	public static void main(String...args) throws JsonProcessingException {
		
		List<CategoryAggregate> list = new ArrayList<>();
		list.add(new CategoryAggregate("Grocerry", 10, 12));
		list.add(new CategoryAggregate("Individual", 100, 1000));
		Map<String, List> map = new HashMap<>();
		map.put("Jan", list);
		
		
		list = new ArrayList<>();
		list.add(new CategoryAggregate("Grocerry", 100, 112));
		list.add(new CategoryAggregate("Individual", 1001, 2000));
		map.put("Feb", list);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(map));
			
	}


	public Map<String, List<CategoryAggregate>> getAggregate() {
		return aggregate;
	}


	public void setAggregate(Map<String, List<CategoryAggregate>> aggregate) {
		this.aggregate = aggregate;
	}
	
	
}
