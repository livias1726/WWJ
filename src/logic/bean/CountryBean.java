package logic.bean;


import java.util.List;

import logic.application.control.ViewBusinessControl;
import logic.application.control.ViewOfferControl;

public class CountryBean {
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getCountries(OfferBean off){
		ViewOfferControl control = ViewOfferControl.getInstance();
		return control.retrieveCountries();
	}
	
	public List<String> getCountries(BusinessInCountryBean bus){
		ViewBusinessControl control = ViewBusinessControl.getInstance();
		return control.retrieveCountries();
	}
}