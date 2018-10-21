package models;

public class DonorCategoryMapping{

	@Override
	public String toString() {
		return "DonorCategoryMapping [donorId=" + donorId + ", organization=" + organization + ", category=" + category
				+ "]";
	}
	private static final long serialVersionUID = 6221647641773321L;
	private String donorId;
	private String organization;
	private String category;
	
	
	
	public DonorCategoryMapping(String donorId, String organization, String category) {
		super();
		this.donorId = donorId;
		this.organization = organization;
		this.category = category;
	}
	public String getDonorId() {
		return donorId;
	}
	public void setDonorId(String donorId) {
		this.donorId = donorId;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((donorId == null) ? 0 : donorId.hashCode());
		result = prime * result + ((organization == null) ? 0 : organization.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DonorCategoryMapping other = (DonorCategoryMapping) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (donorId == null) {
			if (other.donorId != null)
				return false;
		} else if (!donorId.equals(other.donorId))
			return false;
		if (organization == null) {
			if (other.organization != null)
				return false;
		} else if (!organization.equals(other.organization))
			return false;
		return true;
	}
	
	
	
	
}
