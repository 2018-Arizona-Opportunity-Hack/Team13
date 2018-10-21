package models;

public class Donation {

	private String organization;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String spouseName;
	private String salutationGreeting;
	private String streetAddress;
	private String apartment;
	private String city;
	private String state;
	private String zipCode;
	private String donorType;
	private String donationType;
	private String donationSource;
	private String donationDate;
	private String foodItemCategory;
	private String foodItemName;
	private String quantity;
	private String quantityType;
	private String weight;
	private String value;
	private String dollarValue;
	private String memo;
	private String donorId;
	private String isCompany;
	private String category;

	public Donation(String organization, String firstName, String middleName, String lastName, String email,
			String spouseName, String salutationGreeting, String streetAddress, String apartment, String city,
			String state, String zipCode, String donorType, String donationType, String donationSource,
			String donationDate, String foodItemCategory, String foodItemName, String quantity, String quantityType,
			String weight, String value, String dollarValue, String memo, String donorId, String isCompany) {
		super();
		this.organization = organization;
		this.firstName = firstName;
		this.middleName = middleName;
		this.donorId = donorId;

		this.lastName = lastName;
		this.email = email;
		this.isCompany = isCompany;
		this.spouseName = spouseName;
		this.salutationGreeting = salutationGreeting;
		this.streetAddress = streetAddress;
		this.apartment = apartment;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.donorType = donorType;
		this.donationType = donationType;
		this.donationSource = donationSource;
		this.donationDate = donationDate;
		this.foodItemCategory = foodItemCategory;
		this.foodItemName = foodItemName;
		this.quantity = quantity;
		this.quantityType = quantityType;
		this.weight = weight;
		this.value = value;
		this.dollarValue = dollarValue;
		this.memo = memo;
	}
	
	public String getDonorId() {
		return donorId;
	}

	public void setDonorId(String donorId) {
		this.donorId = donorId;
	}

	@Override
	public String toString() {
		return "Donation [organization=" + organization + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", email=" + email + ", spouseName=" + spouseName + ", salutationGreeting="
				+ salutationGreeting + ", streetAddress=" + streetAddress + ", apartment=" + apartment + ", city="
				+ city + ", state=" + state + ", zipCode=" + zipCode + ", donorType=" + donorType + ", donationType="
				+ donationType + ", donationSource=" + donationSource + ", donationDate=" + donationDate
				+ ", foodItemCategory=" + foodItemCategory + ", foodItemName=" + foodItemName + ", quantity=" + quantity
				+ ", quantityType=" + quantityType + ", weight=" + weight + ", value=" + value + ", dollarValue="
				+ dollarValue + ", memo=" + memo + ", donorId=" + donorId + ", isCompany=" + isCompany + "]";
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIsCompany() {
		return isCompany;
	}

	public void setIsCompany(String isCompany) {
		this.isCompany = isCompany;
	}

	public Donation() {

	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getSalutationGreeting() {
		return salutationGreeting;
	}

	public void setSalutationGreeting(String salutationGreeting) {
		this.salutationGreeting = salutationGreeting;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getDonorType() {
		return donorType;
	}

	public void setDonorType(String donorType) {
		this.donorType = donorType;
	}

	public String getDonationType() {
		return donationType;
	}

	public void setDonationType(String donationType) {
		this.donationType = donationType;
	}

	public String getDonationSource() {
		return donationSource;
	}

	public void setDonationSource(String donationSource) {
		this.donationSource = donationSource;
	}

	public String getDonationDate() {
		return donationDate;
	}

	public void setDonationDate(String donationDate) {
		this.donationDate = donationDate;
	}

	public String getFoodItemCategory() {
		return foodItemCategory;
	}

	public void setFoodItemCategory(String foodItemCategory) {
		this.foodItemCategory = foodItemCategory;
	}

	public String getFoodItemName() {
		return foodItemName;
	}

	public void setFoodItemName(String foodItemName) {
		this.foodItemName = foodItemName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getQuantityType() {
		return quantityType;
	}

	public void setQuantityType(String quantityType) {
		this.quantityType = quantityType;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDollarValue() {
		return dollarValue;
	}

	public void setDollarValue(String dollarValue) {
		this.dollarValue = dollarValue;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apartment == null) ? 0 : apartment.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((dollarValue == null) ? 0 : dollarValue.hashCode());
		result = prime * result + ((donationDate == null) ? 0 : donationDate.hashCode());
		result = prime * result + ((donationSource == null) ? 0 : donationSource.hashCode());
		result = prime * result + ((donationType == null) ? 0 : donationType.hashCode());
		result = prime * result + ((donorId == null) ? 0 : donorId.hashCode());
		result = prime * result + ((donorType == null) ? 0 : donorType.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((foodItemCategory == null) ? 0 : foodItemCategory.hashCode());
		result = prime * result + ((foodItemName == null) ? 0 : foodItemName.hashCode());
		result = prime * result + ((isCompany == null) ? 0 : isCompany.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((memo == null) ? 0 : memo.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((organization == null) ? 0 : organization.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((quantityType == null) ? 0 : quantityType.hashCode());
		result = prime * result + ((salutationGreeting == null) ? 0 : salutationGreeting.hashCode());
		result = prime * result + ((spouseName == null) ? 0 : spouseName.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((streetAddress == null) ? 0 : streetAddress.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		Donation other = (Donation) obj;
		if (apartment == null) {
			if (other.apartment != null)
				return false;
		} else if (!apartment.equals(other.apartment))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (dollarValue == null) {
			if (other.dollarValue != null)
				return false;
		} else if (!dollarValue.equals(other.dollarValue))
			return false;
		if (donationDate == null) {
			if (other.donationDate != null)
				return false;
		} else if (!donationDate.equals(other.donationDate))
			return false;
		if (donationSource == null) {
			if (other.donationSource != null)
				return false;
		} else if (!donationSource.equals(other.donationSource))
			return false;
		if (donationType == null) {
			if (other.donationType != null)
				return false;
		} else if (!donationType.equals(other.donationType))
			return false;
		if (donorId == null) {
			if (other.donorId != null)
				return false;
		} else if (!donorId.equals(other.donorId))
			return false;
		if (donorType == null) {
			if (other.donorType != null)
				return false;
		} else if (!donorType.equals(other.donorType))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (foodItemCategory == null) {
			if (other.foodItemCategory != null)
				return false;
		} else if (!foodItemCategory.equals(other.foodItemCategory))
			return false;
		if (foodItemName == null) {
			if (other.foodItemName != null)
				return false;
		} else if (!foodItemName.equals(other.foodItemName))
			return false;
		if (isCompany == null) {
			if (other.isCompany != null)
				return false;
		} else if (!isCompany.equals(other.isCompany))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (memo == null) {
			if (other.memo != null)
				return false;
		} else if (!memo.equals(other.memo))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (organization == null) {
			if (other.organization != null)
				return false;
		} else if (!organization.equals(other.organization))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (quantityType == null) {
			if (other.quantityType != null)
				return false;
		} else if (!quantityType.equals(other.quantityType))
			return false;
		if (salutationGreeting == null) {
			if (other.salutationGreeting != null)
				return false;
		} else if (!salutationGreeting.equals(other.salutationGreeting))
			return false;
		if (spouseName == null) {
			if (other.spouseName != null)
				return false;
		} else if (!spouseName.equals(other.spouseName))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (streetAddress == null) {
			if (other.streetAddress != null)
				return false;
		} else if (!streetAddress.equals(other.streetAddress))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

}
