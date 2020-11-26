package logic.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BusinessControl {
	
	private static BusinessControl instance = null;

    private BusinessControl() {
    	/*Default constructor*/
    }

    public static BusinessControl getInstance() {
        if(instance == null) {
        	instance = new BusinessControl();
        }

        return instance;
    }
    
    public ObservableList<String> retrieveBusinesses(){
    	
		ObservableList<String> list = FXCollections.observableArrayList();
    	/**
    	 * Access the DB and select every business managed by the system...
    	 * 
    	 * EXAMPLE DUMMY
    	 */
    	
    	list.add("Pizza");
    	
    	return list;
    }
}
