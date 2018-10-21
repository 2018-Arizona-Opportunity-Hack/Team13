package util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.hackathon.inventoryserver.service.AggregationService;
import com.hackathon.inventoryserver.service.AggregationServiceImpl;
import com.opencsv.CSVReader;

import models.Donation;
import models.DonorCategoryMapping;
import models.DonorCategoryResponse;

@Component
public class ExtractCSV {

	static Map<String, String> mapInit = CategorySingleton.getInstance();
	
	private static AggregationService aggregationService = new AggregationServiceImpl();

	public static DonorCategoryResponse readCSVFile(CSVReader csvReader, String year, String month) {
		DonorCategoryResponse response = new DonorCategoryResponse(200, "success", month, year);
		Set<DonorCategoryMapping> newDonorCatagoryList = new HashSet<>();
		Set<String> newDonors = new HashSet<>();
		 Map<String, Integer> mapIndex = new HashMap<>();
		 Map<String, String> columnMapping = new HashMap<String, String>();
		 Map<String, List<Donation>> mapDonors = new HashMap<>();
		 mapFields(columnMapping);
		try {
			String[] donationDetails = null;
			List<Donation> donationList = new ArrayList<Donation>();
			int count = 0;
			while ((donationDetails = csvReader.readNext()) != null) {
				if (count == 0) {
					setIndexInMap(donationDetails,mapIndex);
					count++;
				} else {
					Donation don = new Donation();
					List<Donation> donorList = null;
					setMembers(don, donationDetails,columnMapping, mapIndex);
					if (mapInit.containsKey(don.getDonorId())) {

						don.setCategory(mapInit.get(don.getDonorId()));
						if (mapDonors.containsKey(don.getCategory())) {
							donorList = mapDonors.get(don.getCategory());
							donorList.add(don);
							mapDonors.put(don.getCategory(), donorList);
						} else {
							donorList = new ArrayList<>();
							donorList.add(don);
							mapDonors.put(don.getCategory(), donorList);

						}
					} else {
						if ((don.getIsCompany().equals("0") || StringUtils.isBlank(don.getIsCompany())) && StringUtils.isBlank(don.getOrganization())) {
							if (mapDonors.containsKey("Individual")) {
								donorList = mapDonors.get("Individual");
								donorList.add(don);
								mapDonors.put("Individual", donorList);
							} else {
								donorList = new ArrayList<>();
								donorList.add(don);
								mapDonors.put("Individual", donorList);
							}
						} else {
							newDonors.add(don.getDonorId());
							newDonorCatagoryList.add(new DonorCategoryMapping(don.getDonorId(), don.getOrganization(),
									don.getCategory()));
							NewDonerTempStore.addToNewDonerList(don);
						}
					}
					donationList.add(don);
				}
				if (!CollectionUtils.isEmpty(newDonorCatagoryList)) {
					response.setMsg("Some Donors are present in the system. Please categorize them!");
					response.setMapping(newDonorCatagoryList);
					System.out.println("New donors"+ newDonorCatagoryList);
				}
			}
			createFiles(donationList, year, month, mapDonors);
			aggregationService.aggregateMonthlyData(mapDonors, year, month);
		} catch (Exception ee) {
			ee.printStackTrace();
		}

		return response;
	}

	public static void setIndexInMap(String[] donation, Map<String, Integer> mapIndex) {
		int i = 0;
		int length = donation.length;
		for (i = 0; i < length; i++) {
			mapIndex.put(donation[i], i);
		}
	}

	public static void setMembers(Donation donation, String[] donationDetails, Map<String, String> columnMapping, Map<String, Integer> mapIndex)
			throws IllegalAccessException, InvocationTargetException {
		for (Entry<String, String> map : columnMapping.entrySet()) {
			if (mapIndex.containsKey(map.getKey()))
				BeanUtils.setProperty(donation, map.getValue(), donationDetails[mapIndex.get(map.getKey())]);
		}
	}

	public static void mapFields(Map<String, String> columnMapping) {

		columnMapping.put("Donor ID", "donorId");
		columnMapping.put("Quantity", "quantity");
		// columnMapping.put("Weight", "weight");
		columnMapping.put("Donor is a Company", "isCompany");
		columnMapping.put("Street Address", "streetAddress");
		columnMapping.put("Donated On", "donationDate");
		columnMapping.put("Weight (lbs)", "weight");
		columnMapping.put("Email Address", "email");
		columnMapping.put("First Name", "firstName");
		columnMapping.put("Last Name", "lastName");
		columnMapping.put("Company / Organization Name", "organization");

	}

	public static void readCategoryInit(FileReader initCsv, Map<String, String> map) {
		System.out.println("~~~~~~~Initializing the category map~~~~~~");
		CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(initCsv, ',', '"', 1);
			String[] donationDetails = null;
			//csvReader.readNext();
			while ((donationDetails = csvReader.readNext()) != null && donationDetails.length>1) {
				map.put(donationDetails[0], donationDetails[1]);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		System.out.println(map);

	}

	static CSVReader csvReader;

	public static void main(String[] args) throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\kumar\\Downloads\\donors.csv"));
		CSVReader csvReader = new CSVReader(reader);
		readCSVFile(csvReader, "2019", "Aug");

	}

	public static void createFiles(List<Donation> donation, String year, String month, Map<String, List<Donation>> mapDonors) throws IOException {

		if (!StringUtils.isBlank(year) && !StringUtils.isBlank(month) && null != donation) {
			StringBuffer sbPath = new StringBuffer(Constant.DATA_FOLDER);
			// StringBuffer sbPath = new StringBuffer("D:\\Files");
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

			int length = 0;
			String json = ".json";
			int jsonLength = json.length();
			String jsonCategory = null;
			Gson gson = new Gson();
			for (Entry<String, List<Donation>> entry : mapDonors.entrySet()) {
				categoryName = entry.getKey();
				length = categoryName.length();
				sbPath.append("/").append(categoryName).append(json);
				jsonCategory = gson.toJson(entry.getValue());
				file = null;
				file = new File(sbPath.toString());
				try (FileWriter fileWrite = new FileWriter(sbPath.toString())) {
					fileWrite.write(jsonCategory);
					// System.out.println("Successfully Copied JSON Object to File...");
					// System.out.println("\nJSON Object: " + jsonCategory);
				}
				sbPath.delete(sbPath.length() - length - jsonLength - 1, sbPath.length());
			}

		}
	}

}
