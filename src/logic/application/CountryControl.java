package logic.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CountryControl {
	
	private static CountryControl instance = null;

    private CountryControl() {
    	/*Default constructor*/
    }

    public static CountryControl getInstance() {
        if(instance == null) {
        	instance = new CountryControl();
        }

        return instance;
    }
    
    public ObservableList<String> retrieveCountries(){
    	
		ObservableList<String> list = FXCollections.observableArrayList();
    	/**
    	 * Access the DB and select every Country managed by the system...
    	 * 
    	 * EXAMPLE DUMMY
    	 */
    	
    	list.add("Canada");
    	
    	return list;
    }
}
