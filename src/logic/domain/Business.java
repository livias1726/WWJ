package logic.domain;

import java.sql.SQLException;
import java.util.List;

import logic.persistence.dao.BusinessDAO;

public class Business {

	private String category;
	private String name;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getAvailableBusinesses() throws SQLException{
		return BusinessDAO.selectBusinesses();
	}
}
