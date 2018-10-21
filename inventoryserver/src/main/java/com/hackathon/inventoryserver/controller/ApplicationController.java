package com.hackathon.inventoryserver.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.websocket.server.PathParam;

import org.apache.commons.collections.CollectionUtils;
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
import com.hackathon.inventoryserver.service.FileStorageService;
import com.opencsv.CSVReader;

import models.AggregateResponse;
import models.CategoryAggregate;
import models.Donation;
import models.Response;
import util.ExtractCSV;

@RestController
public class ApplicationController {

	@Autowired
	private FileStorageService fileStorageService;

	@PostMapping("/csv/{year}/{month}")
	public Response uploadFile(@RequestParam("file") MultipartFile file, @PathParam("month") String month,
			@PathParam("year") String year) {
		String fileName = fileStorageService.storeFile(file);
		System.out.println(fileName + "::" + month + "::" + year);
		try {
			Reader reader = Files.newBufferedReader(Paths.get(fileName));
			CSVReader csvReader = new CSVReader(reader);
			ExtractCSV.readCSVFile(csvReader);
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

	@GetMapping("/data/{year}/{month}")
	public Response aggregateMonthlyData(@PathParam("month") String month, @PathParam("year") String year,
			@RequestParam("categoryList") List<String> categoryList) {
		Set<String> cList = new HashSet<>(categoryList);
		StringBuilder folder = new StringBuilder();
		folder.append(util.Constant.DATA_FOLER).append(year).append("/").append(month);
		File f = new File(folder.toString());
		List<CategoryAggregate> resultList = new ArrayList<CategoryAggregate>();
		if (f.exists() && f.isDirectory()) {
			try {
				if (null == categoryList || CollectionUtils.isEmpty(categoryList)) {
					File[] listOfFiles = f.listFiles();
					for (File singleFile : listOfFiles) {
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
				} else {
					File[] listOfFiles = f.listFiles();
					for (File singleFile : listOfFiles) {
						if (cList.contains(singleFile.getName())) {
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
