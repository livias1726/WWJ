package logic.application.control;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.domain.Business;
import logic.domain.BusinessInCountry;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;
import logic.presentation.bean.BusinessBean;
import logic.presentation.bean.BusinessInCountryBean;
import logic.presentation.bean.CountryBean;
import logic.service.AbstractFactory;
import logic.service.BusinessFactory;
import logic.service.BusinessInCountryFactory;
import logic.service.Factory;
import logic.service.Types;

public class ViewBusinessControl extends ViewResultsControl{
	
	private static ViewBusinessControl instance = null;

    private ViewBusinessControl() {
    	/*Default constructor*/
    }

    public static ViewBusinessControl getInstance() {
        if(instance == null) {
        	instance = new ViewBusinessControl();
        }

        return instance;
    }
    
    public List<BusinessBean> retrieveBusinesses() throws DatabaseFailureException{
    	AbstractFactory factory = Factory.getInstance().getObject(Types.BUSINESS);
		Business bus = (Business)factory.createObject();
    	
		try {
    		List<Business> list = bus.getAvailableBusinesses();
    		return ((BusinessFactory)factory).extractBusinessBeanList(list);
		} catch (SQLException e) {
			Logger.getLogger(ViewBusinessControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
    }
    
    public List<BusinessInCountryBean> retrieveBusinessesByCategory(BusinessInCountryBean bean) throws NoResultFoundException, DatabaseFailureException {
    	AbstractFactory factory = Factory.getInstance().getObject(Types.BUSINESSINCOUNTRY);
    	BusinessInCountry bus = (BusinessInCountry)factory.createObject();
    	
    	try {
			List<BusinessInCountry> list = bus.getBusinessesByCategory(bean.getCategory());
			return ((BusinessInCountryFactory)factory).extractBusinessInCountryBeanList(list);
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			Logger.getLogger(ViewBusinessControl.class.getName()).log(Level.SEVERE, null, se);
			throw new DatabaseFailureException();
		}
    }
    
    public List<BusinessInCountryBean> retrieveBusinessesByCountry(CountryBean bean) throws NoResultFoundException, DatabaseFailureException {
    	AbstractFactory factory = Factory.getInstance().getObject(Types.BUSINESSINCOUNTRY);
    	BusinessInCountry bus = (BusinessInCountry)factory.createObject();
    	
    	try {
			List<BusinessInCountry> list = bus.getBusinessesByPlace(bean.getName());
			return ((BusinessInCountryFactory)factory).extractBusinessInCountryBeanList(list);
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			Logger.getLogger(ViewBusinessControl.class.getName()).log(Level.SEVERE, null, se);
			throw new DatabaseFailureException();
		}
    }
    
    public List<BusinessInCountryBean> retrieveBusinesses(CountryBean country, BusinessInCountryBean bus) throws NoResultFoundException, DatabaseFailureException{
    	AbstractFactory factory = Factory.getInstance().getObject(Types.BUSINESSINCOUNTRY);
    	BusinessInCountry business = (BusinessInCountry)factory.createObject();
    	
    	try {
			List<BusinessInCountry> list = business.getBusinessesInCountry(country.getName(), bus.getCategory());
			return ((BusinessInCountryFactory)factory).extractBusinessInCountryBeanList(list);
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			Logger.getLogger(ViewBusinessControl.class.getName()).log(Level.SEVERE, null, se);
			throw new DatabaseFailureException();
		}
    }
   
}
