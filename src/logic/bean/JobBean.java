package logic.bean;

import java.util.List;

import logic.application.control.JobControl;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.InvalidFieldException;

public class JobBean {

	private int id;
	private String name;
	private String category;
	
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
	
	public void saveJob() throws InvalidFieldException, DatabaseFailureException {
		if(this.category == null || this.name == null) {
			throw new InvalidFieldException("Please, fill out every field!");
		}		
		JobControl.getInstance().saveNewJob(this);
	}

	public List<JobBean> getJobs() throws DatabaseFailureException {
		return JobControl.getInstance().retrieveJobs();
	}
}
