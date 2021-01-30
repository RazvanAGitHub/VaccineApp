package com.springboot.covid19.rest;

public class VaccineRest {
	private int id;
	private String manufacturer;
	private int minAge;
	private int storageTemperature;
	private int inStock;
	
	VaccineRest() {}

	public VaccineRest(int id, String manufacturer, int minAge, int storageTemperature, int inStock) {
		this.id = id;
		this.manufacturer = manufacturer;
		this.minAge = minAge;
		this.storageTemperature = storageTemperature;
		this.inStock = inStock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public int getStorageTemperature() {
		return storageTemperature;
	}

	public void setStorageTemperature(int storageTemperature) {
		this.storageTemperature = storageTemperature;
	}

	public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	@Override
	public String toString() {
		return "VaccineRest [id=" + id + ", manufacturer=" + manufacturer + ", minAge=" + minAge
				+ ", storageTemperature=" + storageTemperature + ", inStock=" + inStock + "]";
	}
}
