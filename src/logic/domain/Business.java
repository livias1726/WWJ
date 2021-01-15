package logic.domain;

import java.sql.SQLException;
import java.util.List;

import logic.persistence.dao.BusinessDAO;

public class Business {

	protected int busId;
	protected String busCat;
	protected String busName;
	
	public Business() {
		/**/
	}
	
	public Business(String name, String category) {
		this.busName = name;
		this.busCat = category;				
	}

	public int getId() {
		return busId;
	}

	public void setId(int id) {
		this.busId = id;
	}
	
	public String getCategory() {
		return busCat;
	}

	public void setCategory(String category) {
		this.busCat = category;
	}

	public String getName() {
		return busName;
	}

	public void setName(String name) {
		this.busName = name;
	}
	
	public List<Business> getAvailableBusinesses() throws SQLException{
		return BusinessDAO.selectBusinesses();
	}
}
