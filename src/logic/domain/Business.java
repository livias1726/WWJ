package logic.domain;

import java.sql.SQLException;
import java.util.List;

import logic.persistence.dao.BusinessDAO;

public class Business {

	protected int id;
	protected String category;
	protected String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
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
