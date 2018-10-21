package com.hackathon.inventoryserver.service;

import java.util.List;
import java.util.Map;

import models.Donation;

public interface AggregationService {

	public void aggregateMonthlyData(Map<String,List<Donation>> map, String month, String year);
	
}
