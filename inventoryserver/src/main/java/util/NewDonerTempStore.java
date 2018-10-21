package util;

import java.util.HashMap;
import java.util.Map;

import models.Donation;

public class NewDonerTempStore {

	private final static Map<String, Donation> map = new HashMap<>();
	
	
	public static synchronized void addToNewDonerList(Donation donation) {
		map.putIfAbsent(donation.getDonorId(), donation);
	}
	
	
	public static synchronized void flushMap() {
		map.clear();
	}
	
	
	public static Map<String, Donation> getTempDonations() {
		return map;
	}
	
}
