package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

import models.Donation;

public class ExtractCSV {

	/**
	 * static CSVReader csvReader; public static void main(String[] args) throws
	 * IOException {
	 * 
	 * Reader reader =
	 * Files.newBufferedReader(Paths.get("C:\\Users\\kumar\\Downloads\\donors.csv"));
	 * csvReader = new CSVReader(reader); readCSVFile();
	 * 
	 * }
	 */

	static Map<String, Integer> mapIndex = new HashMap<>();
	static Map<String, String> columnMapping = new HashMap<String, String>();

	public static void readCSVFile(CSVReader csvReader) {

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

					setMembers(don, donationDetails);
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
			System.out.println(columnMapping.get(map.getKey()));
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

	}

}
