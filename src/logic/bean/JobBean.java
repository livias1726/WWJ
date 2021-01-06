package logic.bean;

import java.util.List;

import logic.application.control.PublishOfferControl;
import logic.application.control.ViewOfferControl;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.InvalidFieldException;

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
	
	public List<String> getJobNames() throws DatabaseFailureException {
		return PublishOfferControl.getInstance().retrieveJobs();
	}

	public List<String> getJobCategories() throws DatabaseFailureException {
		return ViewOfferControl.getInstance().retrieveJobs();
	}	

	public void saveJob() throws InvalidFieldException, DatabaseFailureException {
		if(this.category == null || this.name == null) {
			throw new InvalidFieldException("Please, fill out every field!");
		}
		
		PublishOfferControl.getInstance().saveNewJob(this);
	}
}
