package models;

import java.util.Date;

public class Donation {

	private String organization;
	private String firstName;
	private String middleName;
	private String lastName;

	public String getDonorId() {
		return donorId;
	}

	public void setDonorId(String donorId) {
		this.donorId = donorId;
	}

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

}
