package logic.application.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.BusinessInCountryBean;
import logic.bean.CountryBean;
import logic.domain.Business;
import logic.domain.BusinessInCountry;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;

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
    
    public List<String> retrieveBusinesses() throws DatabaseFailureException{
		Business business = new Business();
    	try {
			return business.getAvailableBusinesses();
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
    }
    
    public List<BusinessInCountryBean> retrieveBusinessesByName(BusinessInCountryBean bean) throws NoResultFoundException, DatabaseFailureException {
    	BusinessInCountry business = new BusinessInCountry();
    	List<BusinessInCountry> list;
		try {
			list = business.getBusinessesByName(bean.getName());
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}
    	
    	return modelToBean(list);
    }
    
    public List<BusinessInCountryBean> retrieveBusinessesByCountry(CountryBean bean) throws NoResultFoundException, DatabaseFailureException {
    	BusinessInCountry offer = new BusinessInCountry();
    	List<BusinessInCountry> list;
		try {
			list = offer.getBusinessesByPlace(bean.getName());
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}
    	
    	return modelToBean(list);
    }
    
    public List<BusinessInCountryBean> retrieveBusinesses(CountryBean country, BusinessInCountryBean bus) throws NoResultFoundException, DatabaseFailureException{
    	BusinessInCountry offer = new BusinessInCountry();
    	List<BusinessInCountry> list;
		try {
			list = offer.getBusinessesInCountry(country.getName(), bus.getName());
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}

    	return modelToBean(list);
    }

	private List<BusinessInCountryBean> modelToBean(List<BusinessInCountry> src) {
		List<BusinessInCountryBean> dest = new ArrayList<>();
    	for(BusinessInCountry i: src) {
    		BusinessInCountryBean bean = new BusinessInCountryBean();  		
    		
    		bean.setName(i.getName());
    		bean.setDescription(i.getDescription());
    		bean.setCategory(i.getCategory());
    		
    		CountryBean country = new CountryBean();
    		country.setName(i.getCountry().getName());
    		bean.setCountry(country);
    		
    		dest.add(bean);
    	}
    	
    	return dest;
	}
}
