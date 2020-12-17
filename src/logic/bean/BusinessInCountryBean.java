package logic.bean;

import java.util.List;

import logic.application.control.ViewBusinessControl;
import logic.domain.Country;

public class BusinessInCountryBean {

	private String name;
	private String category;
	private float averageManagementCost;
	private float averageEarnings;
	private float description;
	private Country country;
		
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

	public float getAverageManagementCost() {
		return averageManagementCost;
	}

	public void setAverageManagementCost(float averageManagementCost) {
		this.averageManagementCost = averageManagementCost;
	}

	public float getAverageEarnings() {
		return averageEarnings;
	}

	public void setAverageEarnings(float averageEarnings) {
		this.averageEarnings = averageEarnings;
	}

	public float getDescription() {
		return description;
	}

	public void setDescription(float description) {
		this.description = description;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<String> getBusinesses(){
		ViewBusinessControl control = ViewBusinessControl.getInstance();
		return control.retrieveBusinesses();
	}
	
	public List<BusinessInCountryBean> getBusinesses(CountryBean country, BusinessInCountryBean bus){
		ViewBusinessControl controller = ViewBusinessControl.getInstance();
		return controller.retrieveBusinesses(country, bus);
	}
	
	public List<BusinessInCountryBean> getBusinesses(CountryBean country){
		ViewBusinessControl controller = ViewBusinessControl.getInstance();
		return controller.retrieveBusinessesByCountry(country);
	}
	
	public List<BusinessInCountryBean> getBusinesses(BusinessInCountryBean bus){
		ViewBusinessControl controller = ViewBusinessControl.getInstance();
		return controller.retrieveBusinessesByName(bus);
	}
}
