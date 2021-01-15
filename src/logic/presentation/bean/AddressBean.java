package logic.presentation.bean;

public class AddressBean {

	private int id;
	private CountryBean country;
	private String countryName; //used for tableview in company info section
	private String stateBean;
	private String cityBean;
	private String pcBean;
	private String streetBean;
	private int numBean;

	public String getCity() {
		return cityBean;
	}

	public void setCity(String city) {
		this.cityBean = city;
	}

	public String getPostalCode() {
		return pcBean;
	}

	public void setPostalCode(String postalCode) {
		this.pcBean = postalCode;
	}

	public String getStreet() {
		return streetBean;
	}

	public void setStreet(String street) {
		this.streetBean = street;
	}

	public int getNumber() {
		return numBean;
	}

	public void setNumber(int number) {
		this.numBean = number;
	}

	public String getState() {
		return stateBean;
	}
	
	public void setState(String name) {
		this.stateBean = name;
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
		return this.countryName + ", " + this.stateBean + ", " + this.cityBean + ", " + this.streetBean + ", " + this.numBean + ", " + this.pcBean;
	}

	public String buildMapAddress() {
		String str = this.streetBean.replace(".", "").replace(" ", "+");
		return str + ",+" + this.numBean + "," + this.pcBean.replace(" ", "+") + "+" + this.countryName;
	}
}
