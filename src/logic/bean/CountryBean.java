package logic.bean;


import javafx.collections.ObservableList;
import logic.application.CountryControl;

public class CountryBean {
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ObservableList<String> getCountries(){
		CountryControl control = CountryControl.getInstance();
		return control.retrieveCountries();
	}
}
