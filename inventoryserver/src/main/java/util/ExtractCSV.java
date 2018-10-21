package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

import models.Donation;

public class ExtractCSV {

	
	
	static Map<String,String> mapInit = CategorySingleton.getInstance();

	static Map<String, Integer> mapIndex = new HashMap<>();
	static Map<String, String> columnMapping = new HashMap<String, String>();
	static Set<String> newDonors =new HashSet<>();
	static Map<String,List<Donation>> mapDonors = new HashMap<>();

	public static void readCSVFile(CSVReader csvReader,String year, String month) {

		// Field[] fields = Donation.class.getFields();
		mapFields();
		try {
			// csvReader = new CSVReader(csvFileReader, ',', '"', 1);
			String[] donationDetails = null;
			List<Donation> donationList = new ArrayList<Donation>();
			int count = 0;
			while ((donationDetails = csvReader.readNext()) != null) {

				if (count == 0) {
					setIndexInMap(donationDetails);
					count++;
				} else {

					Donation don = new Donation();
					List<Donation> donorList = null;
					setMembers(don, donationDetails);
					if(mapInit.containsKey(don.getDonorId())) {
						don.setCategory(mapInit.get(don.getDonorId()));
						if(mapDonors.containsKey(don.getCategory()))
						{
							donorList = mapDonors.get(don.getCategory());
							donorList.add(don);
						mapDonors.put(don.getCategory(), donorList);
						}
						else {
							donorList = new ArrayList<>();
							donorList.add(don);
							mapDonors.put(don.getCategory(), donorList);
							
						}
					}
					else
						newDonors.add(don.getDonorId());
					donationList.add(don);
				}
			}
			
			
			

			for (Donation e : donationList) {

				System.out.println(e.getDonorId());
				System.out.println("success");
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

	public static void setIndexInMap(String[] donation) {
		int i = 0;
		int length = donation.length;
		for (i = 0; i < length; i++) {
			mapIndex.put(donation[i], i);
		}
	}

	public static void setMembers(Donation donation, String[] donationDetails)
			throws IllegalAccessException, InvocationTargetException {
		int i = 0;
		int length;
		for (Entry<String, String> map : columnMapping.entrySet()) {
		//	System.out.println(columnMapping.get(map.getKey()));
			if (mapIndex.containsKey(map.getKey()))
				BeanUtils.setProperty(donation, map.getValue(), donationDetails[mapIndex.get(map.getKey())]);
		}
	}

	public static void mapFields() {

		columnMapping.put("Donor ID", "donorId");
		columnMapping.put("Quantity", "quantity");
		columnMapping.put("Weight", "weight");
		columnMapping.put("Donor is a Company", "isCompany");
		columnMapping.put("Street Address", "streetAddress");
		columnMapping.put("Donated On", "donationDate");
		columnMapping.put("Weight (lbs)", "weight");
		columnMapping.put("Email Address", "email");
		columnMapping.put("First Name", "firstName");
		columnMapping.put("Last Name", "lastName");


	}
	
	  static CSVReader csvReader; public static void main(String[] args) throws
	  IOException {
		  Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\kumar\\Downloads\\donors.csv"));
			CSVReader csvReader = new CSVReader(reader);
			readCSVFile(csvReader, "2019", "Aug");
		  
	  createFiles(null, "2019", "Jan");
	 }
	 
	
	
	public static void createFiles(List<Donation> donation, String year, String month) throws IOException
	{
		//StringBuffer sbPath = new StringBuffer("D:\\Files");
		StringBuffer sbPath = new StringBuffer(Constant.DATA_FOLDER);
		sbPath.append("\\").append(year);
		String categoryName = null;
		File file = new File(sbPath.toString());
		if(!file.exists())
		{
			if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
		}
		sbPath.append("/").append(month);
		
		file = null;
		file = new File(sbPath.toString());
		if(!file.exists())
		{
			if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
		}
		
		int length =0;
		String json = ".json";
		int jsonLength = json.length();
		String jsonCategory =null;
		Gson gson = new Gson();
		for(Entry<String,List<Donation>> entry:mapDonors.entrySet())
		{
			categoryName = entry.getKey();
			length = categoryName.length();
			sbPath.append("/").append(categoryName).append(json);
			jsonCategory = gson.toJson(entry.getValue());
			file =null;
			file = new File(sbPath.toString());
			try (FileWriter fileWrite = new FileWriter(sbPath.toString())) {
				fileWrite.write(jsonCategory);
				System.out.println("Successfully Copied JSON Object to File...");
				System.out.println("\nJSON Object: " + jsonCategory);
			}
			sbPath.delete(sbPath.length()-length-jsonLength-1, sbPath.length());
		}
		
		
	}
	
	
	public static void readCategoryInit(FileReader initCsv, Map<String,String> map) {
		System.out.println("~~~~~~~Initializing the category map~~~~~~");
		CSVReader csvReader = null;		
		try {
			csvReader = new CSVReader(initCsv, ',', '"', 1);
			String[] donationDetails = null;
			csvReader.readNext();
			while ((donationDetails = csvReader.readNext()) != null) {
				map.put(donationDetails[0], donationDetails[1]);
			}
 		} catch (Exception ee) {
			ee.printStackTrace();}
	}

}
