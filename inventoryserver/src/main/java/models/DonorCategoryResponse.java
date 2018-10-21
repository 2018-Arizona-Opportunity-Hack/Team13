package models;

import java.util.HashSet;
import java.util.Set;

public class DonorCategoryResponse extends Response {

	private static final long serialVersionUID = 4963201822148416584L;

	public DonorCategoryResponse(int code, String msg) {
		super(code, msg);
	}

	private Set<DonorCategoryMapping> newDonors = new HashSet<>();

	public Set<DonorCategoryMapping> getMapping() {
		return newDonors;
	}

	public void setMapping(Set<DonorCategoryMapping> mapping) {
		this.newDonors.addAll(mapping);
	}

}
