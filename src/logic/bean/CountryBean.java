package logic.bean;


import java.util.List;

import logic.application.control.ViewResultsControl;
import logic.exceptions.DatabaseFailureException;

public class CountryBean {
	private String name;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getCountries() throws DatabaseFailureException{
		return ViewResultsControl.getInstance().retrieveCountries();
	}
	
}