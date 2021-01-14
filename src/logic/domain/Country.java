package logic.domain;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import logic.persistence.dao.CountryDAO;

public class Country implements Serializable{

	private static final long serialVersionUID = 8047822125312096938L;	
	private String name;
	private String currency;
	private Float livingExpense;
	private String exampleCity;

	public Country() {
		/**/
	}
	
	public Country(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Float getLivingExpense() {
		return livingExpense;
	}

	public void setLivingExpense(Float livingExpense) {
		this.livingExpense = livingExpense;
	}

	public String getExampleCity() {
		return exampleCity;
	}

	public void setExampleCity(String exampleCity) {
		this.exampleCity = exampleCity;
	}

	public List<String> getAvailableCountries() throws SQLException{
		return CountryDAO.selectCountries();
	}
}
