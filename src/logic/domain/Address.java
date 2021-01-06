package logic.domain;

import java.io.Serializable;

public class Address implements Serializable{
	private static final long serialVersionUID = -1805865521526758480L;
	
	private int id;
	private Country country;
	private String state;
	private String city;
	private int postalCode;
	private String street;
	private int number;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getState() {
		return state;
	}
	
	public void setState(String name) {
		this.state = name;
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return this.getStreet() + ", " + this.getNumber() + ", " + this.getPostalCode() + ", " + this.getCity() + ", " + this.getState() + ", " + this.getCountry();
	}
}
