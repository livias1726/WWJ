package logic.domain;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import logic.persistence.dao.CountryDAO;

public class Country implements Serializable{

	private static final long serialVersionUID = 8047822125312096938L;	
	private String name;
	private String currency;

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


	public List<String> getAvailableCountries() throws SQLException{
		return CountryDAO.selectCountries();
	}
}
