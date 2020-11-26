package logic.bean;

import javafx.collections.ObservableList;
import logic.application.BusinessControl;

public class BusinessBean {
	private String name;
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ObservableList<String> getBusinesses(){
		BusinessControl control = BusinessControl.getInstance();
		return control.retrieveBusinesses();
	}
}
