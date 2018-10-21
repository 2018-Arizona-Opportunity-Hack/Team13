package com.hackathon.inventoryserver.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.inventoryserver.service.AggregationService;
import com.hackathon.inventoryserver.service.FileStorageService;
import com.opencsv.CSVReader;

import models.AggregateResponse;
import models.CategoryAggregate;
import models.MonthlyAggregateResponse;
import models.Response;
import models.YearlyResponse;
import util.Constant;
import util.ExtractCSV;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
public class ApplicationController {

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private AggregationService aggregationService;

	@PostMapping("/csv/{year}/{month}")
	public Response uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("month") String month,
			@PathVariable("year") String year) {

		String fileName = fileStorageService.storeFile(file);
		System.out.println(fileName + "::" + month + "::" + year);
		try {
			Reader reader = Files.newBufferedReader(Paths.get(fileName));
			CSVReader csvReader = new CSVReader(reader);
			ExtractCSV.readCSVFile(csvReader, year, month);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Response(200, "success");
	}

	@GetMapping("/csv/{year}/{month}")
	public Response exportCsv(@RequestParam("file") MultipartFile file, @PathVariable("month") String month,
			@PathVariable("year") String year) {
		return new Response(200, "success");
	}

	@GetMapping("/data/{year}")
	public YearlyResponse aggregateYearlyData(@PathVariable("year") String year,
			@RequestParam(value = "categoryList", required = false) List<String> categoryList) {
		YearlyResponse response = new YearlyResponse(200, "success");
		List<MonthlyAggregateResponse> resultList = new ArrayList<MonthlyAggregateResponse>();

		StringBuilder path = new StringBuilder();
		path.append(Constant.DATA_FOLDER).append(year);
		File file = new File(path.toString());
		String[] directories = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File current, String name) {
				return new File(current, name).isDirectory();
			}
		});
		ObjectMapper mapper = new ObjectMapper();
		String content = "";
		try {
			for (String monthDir : directories) {
				String p = path.toString()+"/"+monthDir + "/aggregate.json";
				File f = new File(p);
				if (f.exists()) {
					content = new String(Files.readAllBytes(Paths.get(p)));
					System.out.println(content);
					List<CategoryAggregate> categoiesJsonList = mapper.readValue(content,
							new TypeReference<List<CategoryAggregate>>() {
							});
					resultList.add(new MonthlyAggregateResponse(monthDir, categoiesJsonList));
				}

			}
			response.setResultList(resultList);

		} catch (IOException e) {
			e.printStackTrace();
			response.setCode(400);
			response.setMsg("Failure");
		}
		return response;
	}

	@GetMapping("/data/{year}/{month}")
	public Response aggregateMonthlyData(@PathVariable("month") String month, @PathVariable("year") String year,
			@RequestParam(value = "categoryList", required = false) List<String> categoryList) {
		Set<String> cList;
		if (null!=categoryList) {
			cList = new HashSet<>(categoryList);
		}
		StringBuilder path = new StringBuilder();
		path.append(util.Constant.DATA_FOLDER).append(year).append("/").append(month).append("/aggregate.json");
		File f = new File(path.toString());

		List<CategoryAggregate> resultList = new ArrayList<CategoryAggregate>();
		if (f.exists()) {
			try {
				if (null == categoryList || CollectionUtils.isEmpty(categoryList)) {
					ObjectMapper mapper = new ObjectMapper();
					JsonFactory factory = new JsonFactory();
					List<CategoryAggregate> categoryAggregateList = null;
					JsonParser jp = factory.createJsonParser(f);
					TypeReference<List<CategoryAggregate>> tRef = new TypeReference<List<CategoryAggregate>>() {
					};
					categoryAggregateList = mapper.readValue(jp, tRef);
					resultList.addAll(categoryAggregateList);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			return new AggregateResponse(200, "", resultList);
		} else {
			return new AggregateResponse(400, "No data exists for the month: " + month + "and year: " + year, null);
		}

	}

}
