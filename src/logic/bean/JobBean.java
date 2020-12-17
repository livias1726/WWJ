package logic.bean;

import java.util.List;

import logic.application.control.ViewOfferControl;

public class JobBean {
	
	public JobBean() {
		/*Constructor*/
	}
	
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
	
	public List<String> getJobs(){
		ViewOfferControl control = ViewOfferControl.getInstance();
		return control.retrieveJobs();
	}	
}
