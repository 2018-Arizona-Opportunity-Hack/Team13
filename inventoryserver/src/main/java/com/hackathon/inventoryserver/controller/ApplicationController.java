package com.hackathon.inventoryserver.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.apache.commons.collections.CollectionUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
import models.Donation;
import models.MonthlyAggregateResponse;
import models.Response;
import models.YearlyResponse;
import util.Constant;
import util.ExtractCSV;

@RestController
public class ApplicationController {

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private AggregationService aggregationService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/csv/{year}/{month}")
	public Response uploadFile(@RequestParam("file") MultipartFile file, @PathParam("month") String month,
			@PathParam("year") String year) {

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
	public Response exportCsv(@RequestParam("file") MultipartFile file, @PathParam("month") String month,
			@PathParam("year") String year) {
		return new Response(200, "success");
	}

	@GetMapping("/data/{year}")
	public Response aggregateYearlyData(@PathParam("year") String year,
			@RequestParam("categoryList") List<String> categoryList) {
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
				File f = new File(monthDir + "/aggregate.json");
				if (f.exists()) {
					content = new String(Files.readAllBytes(Paths.get(monthDir + "/aggregate.json")));
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
	public Response aggregateMonthlyData(@PathParam("month") String month, @PathParam("year") String year,
			@RequestParam("categoryList") List<String> categoryList) {
		Set<String> cList = new HashSet<>(categoryList);
		StringBuilder folder = new StringBuilder();
		folder.append(util.Constant.DATA_FOLDER).append(year).append("/").append(month);
		File f = new File(folder.toString());

		List<CategoryAggregate> resultList = new ArrayList<CategoryAggregate>();
		if (f.exists() && f.isDirectory()) {
			try {
				if (null == categoryList || CollectionUtils.isEmpty(categoryList)) {
					File[] listOfFiles = f.listFiles();

					for (File singleFile : listOfFiles) {
						if (!singleFile.getName().equals("aggregation.json")) {

							ObjectMapper mapper = new ObjectMapper();
							JsonFactory factory = new JsonFactory();
							List<Donation> donationList = null;
							JsonParser jp = factory.createJsonParser(singleFile);
							TypeReference<List<Donation>> tRef = new TypeReference<List<Donation>>() {
							};
							donationList = mapper.readValue(jp, tRef);
							CategoryAggregate row = new CategoryAggregate();
							for (Donation donation : donationList) {
								row.addUp(singleFile.getName(), donation.getWeight(), donation.getDollarValue());
							}
							System.out.println(row);
							resultList.add(row);

						}
					}
				} else {
					File[] listOfFiles = f.listFiles();
					for (File singleFile : listOfFiles) {
						if (cList.contains(singleFile.getName()) && !singleFile.getName().equals("aggregation.json")) {
							ObjectMapper mapper = new ObjectMapper();
							JsonFactory factory = new JsonFactory();
							List<Donation> donationList = null;
							JsonParser jp = factory.createJsonParser(singleFile);
							TypeReference<List<Donation>> tRef = new TypeReference<List<Donation>>() {
							};
							donationList = mapper.readValue(jp, tRef);
							CategoryAggregate row = new CategoryAggregate();
							for (Donation donation : donationList) {
								row.addUp(singleFile.getName(), donation.getWeight(), donation.getDollarValue());
							}
							System.out.println(row);
							resultList.add(row);
						}

					}

				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return new AggregateResponse(200, "", resultList);
		} else {
			return new AggregateResponse(400, "No data exists for the month: " + month + "and year: " + year, null);
		}

	}

}
