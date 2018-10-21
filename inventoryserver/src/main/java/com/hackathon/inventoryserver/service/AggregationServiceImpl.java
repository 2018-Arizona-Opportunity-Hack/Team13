package com.hackathon.inventoryserver.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.CategoryAggregate;
import models.Donation;
import util.Constant;

@Service
public class AggregationServiceImpl implements AggregationService {

	@Async("processExecutor")
	@Override
	public void aggregateMonthlyData(Map<String, List<Donation>> map, String month, String year) {
		try {
			System.out.println("Entering the async method to calculate the category wise aggregate per month ");
			StringBuilder path = new StringBuilder();
			path.append(Constant.DATA_FOLDER).append(year).append("/").append(month).append("/aggregate.json");
			ObjectMapper mapper = new ObjectMapper();
			List<CategoryAggregate> result = new ArrayList<>();
			for (String cat : map.keySet()) {
				CategoryAggregate category = new CategoryAggregate();
				for (Donation d : map.get(cat)) {
					category.addUp(cat, d.getWeight(), d.getDollarValue());
				}
				result.add(category);
			}

			try {
				mapper.writeValue(new File(path.toString()), result);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception ie) {
			System.out.println("Exception while processing the aggregate");
		}
	}
}