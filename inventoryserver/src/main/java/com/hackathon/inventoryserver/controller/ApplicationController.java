package com.hackathon.inventoryserver.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.io.IOUtils; 



import org.apache.commons.collections.CollectionUtils;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hackathon.inventoryserver.service.AggregationService;
import com.hackathon.inventoryserver.service.FileStorageService;
import com.opencsv.CSVReader;

import models.AggregateResponse;
import models.CategoryAggregate;

import models.Donation;
import models.Donations;

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
	
	
	@PostMapping("/data/missingCategory/{year}/{month}")
	public Response addmissingCategory(@RequestBody List<Donation> donation, @PathVariable String year, @PathVariable String month)
		{
		if (!StringUtils.isBlank(year) && !StringUtils.isBlank(month) && null != donation) {
			StringBuffer sbPath = new StringBuffer(Constant.DATA_FOLDER);
			sbPath.append("/").append(year);
			String categoryName = null;
			File file = new File(sbPath.toString());
			if (!file.exists()) {
				if (file.mkdir()) {
					System.out.println("Directory is created!");
				} else {
					System.out.println("Failed to create directory!");
				}
			}
			sbPath.append("/").append(month);

			file = null;
			file = new File(sbPath.toString());
			if (!file.exists()) {
				if (file.mkdir()) {
					// System.out.println("Directory is created!");
				} else {
					System.out.println("Failed to create directory!");
				}
			}

			Map<String,Double> categoryWeight = new HashMap<>();
			Map<String,List<Donation>> newDonors = new HashMap<>();
			int length = donation.size();
			if(length>0)
			{
				int i=0;
				Donation don =null;
				Double weight=0.0;
				
				String category =null;
				List<Donation> donorList ;
				
			for(i=0;i<length;i++)
			{
				
				weight =0.0;
				don = donation.get(i);
				category =don.getCategory();
				
				if(categoryWeight.containsKey(don.getCategory()))
				{
					weight = Double.parseDouble(don.getWeight());
					
					categoryWeight.put(don.getCategory(), categoryWeight.get(don.getCategory()+weight));
				}
				else {
					categoryWeight.put(don.getCategory(), weight);
				}
				if (newDonors.containsKey(don.getCategory())) {
					donorList = newDonors.get(don.getCategory());
					donorList.add(don);
					newDonors.put(don.getCategory(), donorList);
				} else {
					donorList = new ArrayList<>();
					donorList.add(don);
					newDonors.put(don.getCategory(), donorList);

					}
				
				
				
				}
			try {
				addtoFiles(categoryWeight,newDonors,sbPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			}
			
			
			
			}
		return null;
	}

	private void addtoFiles(Map<String, Double> categoryWeight, Map<String, List<Donation>> newDonors,
			StringBuffer sbPath) throws IOException {
		String json =".json";
		int jsonLength = json.length();
		String aggregate ="aggregate";
		int lengthAg = aggregate.length();
		
		
//String jsonTxt = IOUtils.toString( is );

		String jsonString = null;
				Gson gson = new Gson();
			
				sbPath.append("/").append(aggregate).append(json);
				InputStream is =   ApplicationController.class.getResourceAsStream( sbPath.toString());
				jsonString = IOUtils.toString( is );
				
				Map<String,Double> mapWeight = new ObjectMapper().readValue(jsonString, HashMap.class);
				is.close();
				
				String categoryName=null;
				Double weight = 0.0D;
				for(Entry<String,Double> category:categoryWeight.entrySet())
				{
					weight =0.0D;
					categoryName = category.getKey();
					if(mapWeight.containsKey(categoryName))
					{
						weight = mapWeight.get(categoryName)+category.getValue();
						mapWeight.put(categoryName, weight);
					}else {
						mapWeight.put(categoryName, category.getValue());
					}
				}
				
				File file = new File(sbPath.toString());
						try (FileWriter fileWrite = new FileWriter(sbPath.toString())) {
							fileWrite.write(gson.toJson(mapWeight));
							fileWrite.close();
							// System.out.println("Successfully Copied JSON Object to File...");
							System.out.println("\nJSON Object: " + gson.toJson(mapWeight));
						}
						
						sbPath.delete(sbPath.length() - lengthAg - jsonLength - 1, sbPath.length());	
						
						List<Donation> dons = null;
						for(Entry<String,List<Donation>> entry: newDonors.entrySet())
						{
							
							sbPath.append(entry.getKey()).append(json);
							
							File fileCat = new File(sbPath.toString());
							
							if(fileCat.exists())
							{
								InputStream input =ApplicationController.class.getResourceAsStream(sbPath.toString());
								jsonString = IOUtils.toString( input );
								input.close();
								//List<Student> participantJsonList = mapper.readValue(jsonString, new TypeReference<List<Student>>(){});
								dons =new ObjectMapper().readValue(jsonString,new TypeReference<List<Donation>>(){});
								dons.addAll(entry.getValue());
								
								try (FileWriter fileWrite = new FileWriter(sbPath.toString())) {
									fileWrite.write(gson.toJson(dons));
									fileWrite.close();
									// System.out.println("Successfully Copied JSON Object to File...");
									System.out.println("\nJSON Object: " + gson.toJson(dons));
								}
								
								
							}else {
								try (FileWriter fileWrite = new FileWriter(sbPath.toString())) {
									fileWrite.write(gson.toJson(entry.getValue()));
									fileWrite.close();
									// System.out.println("Successfully Copied JSON Object to File...");
									System.out.println("\nJSON Object: " + gson.toJson(entry.getValue()));
								}
							}
						}
		
	}


}
