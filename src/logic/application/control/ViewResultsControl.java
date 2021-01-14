package logic.application.control;

import java.sql.SQLException;
import java.util.List;

import logic.domain.Country;
import logic.exceptions.DatabaseFailureException;

public class ViewResultsControl {
	
	private static ViewResultsControl instance = null;

    protected ViewResultsControl() {
    	/*Default constructor*/
    }

    public static ViewResultsControl getInstance() {
        if(instance == null) {
        	instance = new ViewResultsControl();
        }

        return instance;
    }
    
    public List<String> retrieveCountries() throws DatabaseFailureException{  	
   
    	try {
			return new Country().getAvailableCountries();
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
    }
    
}
