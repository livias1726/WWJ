package logic.bean;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logic.exceptions.BadAddressException;

public class AddressBean {

	private CountryBean country;
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
	
	public CountryBean getCountry() {
		return country;
	}

	public void setCountry(CountryBean country) {
		this.country = country;
	}

	public void tokenizerAddress(String text) throws BadAddressException {
		StringTokenizer token = new StringTokenizer(text, ", ");
		
		verifyAddressSyntax(text);
		
		CountryBean c = new CountryBean();
		c.setName(token.nextToken());
		this.setCountry(c);
		
		this.setState(token.nextToken());
		this.setPostalCode(Integer.parseInt(token.nextToken()));
		this.setStreet(token.nextToken());
		this.setNumber(Integer.parseInt(token.nextToken()));	
	}

	private void verifyAddressSyntax(String str) throws BadAddressException {
		Pattern pattern = Pattern.compile("\\D+, \\d+, \\d+, \\D+, \\D+, \\D+");
    	Matcher matcher = pattern.matcher(str);
    	if(!matcher.matches()) {
			throw new BadAddressException(str + "is not a valid address. Please, follow the labels.");	
		}
	}
}
