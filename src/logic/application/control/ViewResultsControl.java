package logic.application.control;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.domain.Country;
import logic.exceptions.DatabaseFailureException;
import logic.service.Entity;
import logic.service.Factory;
import logic.service.Types;

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
    	Entity factory = Factory.getInstance().getObject(Types.COUNTRY);
		Country country = (Country)factory.createObject();
    	try {
			return country.getAvailableCountries();
		} catch (SQLException e) {
			Logger.getLogger(ViewResultsControl.class.getName()).log(Level.SEVERE, "SQLException thrown", e);
			throw new DatabaseFailureException();
		}
    }
    
}
