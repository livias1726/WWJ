package logic.bean;

import java.util.List;

import logic.application.control.ViewOfferControl;
import logic.exceptions.DatabaseFailureException;

public class JobBean {
	
	private String name;
	private String category;
	
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
	
	public List<String> getJobs() throws DatabaseFailureException{
		return ViewOfferControl.getInstance().retrieveJobs();
	}	
}
