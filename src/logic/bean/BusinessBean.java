package logic.bean;

import java.util.List;

import logic.application.control.ViewBusinessControl;
import logic.exceptions.DatabaseFailureException;

public class BusinessBean {
	
	protected int id;
	protected String category;
	protected String name;
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public List<String> getBusinesses() throws DatabaseFailureException{
		return ViewBusinessControl.getInstance().retrieveBusinesses();
	}
}
