package logic.application.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.domain.Business;
import logic.domain.BusinessInCountry;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;
import logic.presentation.bean.BusinessBean;
import logic.presentation.bean.BusinessInCountryBean;
import logic.presentation.bean.CountryBean;

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
		Business business = new Business();
		List<BusinessBean> bus = new ArrayList<>();
    	try {
    		for(Business i: business.getAvailableBusinesses()) {
    			BusinessBean bean = new BusinessBean();
				bean.setId(i.getId());
				bean.setName(i.getName());
				bean.setCategory(i.getCategory());
				
				bus.add(bean);
    		}
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
    	
    	return bus;
    }
    
    public List<BusinessInCountryBean> retrieveBusinessesByCategory(BusinessInCountryBean bean) throws NoResultFoundException, DatabaseFailureException {
    	BusinessInCountry business = new BusinessInCountry();
    	List<BusinessInCountry> list;
		try {
			list = business.getBusinessesByCategory(bean.getCategory());
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}
    	
    	return modelToBean(list);
    }
    
    public List<BusinessInCountryBean> retrieveBusinessesByCountry(CountryBean bean) throws NoResultFoundException, DatabaseFailureException {
    	BusinessInCountry business = new BusinessInCountry();
    	List<BusinessInCountry> list;
		try {
			list = business.getBusinessesByPlace(bean.getName());
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
			list = offer.getBusinessesInCountry(country.getName(), bus.getCategory());
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
    		
    		bean.setId(i.getId());
    		bean.setName(i.getName());
    		bean.setCategory(i.getCategory());
    		bean.setDescription(i.getDescription());
    		
    		CountryBean country = new CountryBean();
    		country.setName(i.getCountry().getName());
    		country.setCurrency(i.getCountry().getCurrency());
    		bean.setCountry(country);
    		
    		bean.setAverageEarnings(i.getAverageEarnings());
    		bean.setAverageCost(i.getAverageCost());
    		
    		dest.add(bean);
    	}
    	
    	return dest;
	}
}
