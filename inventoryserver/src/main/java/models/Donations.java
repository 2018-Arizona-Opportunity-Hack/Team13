package models;

import java.util.List;

public class Donations {

	private List<Donation> donations;

	public Donations(List<Donation> donations) {
		super();
		this.donations = donations;
	}

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}
	
	
}
