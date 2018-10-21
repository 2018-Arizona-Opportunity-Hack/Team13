package models;

import java.util.HashSet;
import java.util.Set;

public class DonorCategoryResponse extends Response {

	private static final long serialVersionUID = 4963201822148416584L;

	public DonorCategoryResponse(int code, String msg, String month, String year) {
		super(code, msg);
		this.month = month;
		this.year = year;
	}
	
	public DonorCategoryResponse(int code, String msg) {
		super(code, msg);
	}
	private String month;
	private String year;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	private Set<DonorCategoryMapping> newDonors = new HashSet<>();

	public Set<DonorCategoryMapping> getMapping() {
		return newDonors;
	}

	public void setMapping(Set<DonorCategoryMapping> mapping) {
		this.newDonors.addAll(mapping);
	}

}
