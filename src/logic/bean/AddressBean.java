package logic.bean;

public class AddressBean {

	private int id;
	private CountryBean country;
	private String countryName; //used for tableview in company info section
	private String state;
	private String city;
	private String postalCode;
	private String street;
	private int number;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
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
	
	public CountryBean getCountry() {
		return country;
	}

	public void setCountry(CountryBean country) {
		this.country = country;
		this.countryName = country.getName();
	}

	public String getCountryName() {
		return countryName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return this.countryName + ", " + this.state + ", " + this.city + ", " + this.street + ", " + this.number + ", " + this.postalCode;
	}
}
