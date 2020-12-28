package logic.domain;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import logic.persistence.dao.CountryDAO;

public class Country implements Serializable{

	private static final long serialVersionUID = 8047822125312096938L;
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAvailableCountries() throws SQLException{
		return CountryDAO.selectCountries();
	}
}
