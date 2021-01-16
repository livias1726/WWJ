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
import logic.service.BusinessFactory;

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
    	try {
    		List<Business> list = BusinessFactory.getInstance().createBusiness().getAvailableBusinesses();
    		return BusinessFactory.getInstance().extractBusinessBeanList(list);
		} catch (SQLException e) {
			Logger.getLogger(ViewBusinessControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
    }
    
    public List<BusinessInCountryBean> retrieveBusinessesByCategory(BusinessInCountryBean bean) throws NoResultFoundException, DatabaseFailureException {
		try {
			List<BusinessInCountry> list = BusinessFactory.getInstance().createBusiness().getBusinessesByCategory(bean.getCategory());
			return BusinessFactory.getInstance().extractBusinessInCountryBeanList(list);
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			Logger.getLogger(ViewBusinessControl.class.getName()).log(Level.SEVERE, null, se);
			throw new DatabaseFailureException();
		}
    }
    
    public List<BusinessInCountryBean> retrieveBusinessesByCountry(CountryBean bean) throws NoResultFoundException, DatabaseFailureException {
		try {
			List<BusinessInCountry> list = BusinessFactory.getInstance().createBusiness().getBusinessesByPlace(bean.getName());
			return BusinessFactory.getInstance().extractBusinessInCountryBeanList(list);
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			Logger.getLogger(ViewBusinessControl.class.getName()).log(Level.SEVERE, null, se);
			throw new DatabaseFailureException();
		}
    }
    
    public List<BusinessInCountryBean> retrieveBusinesses(CountryBean country, BusinessInCountryBean bus) throws NoResultFoundException, DatabaseFailureException{
		try {
			List<BusinessInCountry> list = BusinessFactory.getInstance().createBusiness().getBusinessesInCountry(country.getName(), bus.getCategory());
			return BusinessFactory.getInstance().extractBusinessInCountryBeanList(list);
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			Logger.getLogger(ViewBusinessControl.class.getName()).log(Level.SEVERE, null, se);
			throw new DatabaseFailureException();
		}
    }
}
