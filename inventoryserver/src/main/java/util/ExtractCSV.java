package util;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

import models.Donation;

public class ExtractCSV {
	
	public static void readCSVFile(FileReader csvFileReader) {
		CSVReader csvReader = null;

		try {
			csvReader = new CSVReader(csvFileReader, ',', '"', 1);
			String[] donationDetails = null;
			List<Donation> donationList = new ArrayList<Donation>();

			while ((donationDetails = csvReader.readNext()) != null) {
				System.out.println(donationDetails);
				Donation emp = new Donation();
				donationList.add(emp);
			}

			for (Donation e : donationList) {
				System.out.println("success");
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

}
