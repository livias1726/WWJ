package logic.application.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.BusinessInCountryBean;
import logic.bean.CountryBean;
import logic.domain.Business;
import logic.domain.BusinessInCountry;
import logic.exceptions.DatabaseFailureException;

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
    
    public List<BusinessInCountryBean> retrieveBusinessesByName(BusinessInCountryBean bean) {
    	BusinessInCountry business = new BusinessInCountry();
    	List<BusinessInCountry> list = business.getBusinessesByName();
    	
    	/*DUMMY BEHAVIOR*/
    	List<BusinessInCountryBean> res = new ArrayList<>();
    	for(BusinessInCountry i: list) {
    		BusinessInCountryBean item = new BusinessInCountryBean();
    		
    		
    		res.add(item);
    	}
    	
    	return res;
    }
    
    public List<BusinessInCountryBean> retrieveBusinessesByCountry(CountryBean bean) {
    	BusinessInCountry offer = new BusinessInCountry();
    	List<BusinessInCountry> list = offer.getBusinessesByPlace();
    	
    	/*DUMMY BEHAVIOR*/
    	List<BusinessInCountryBean> res = new ArrayList<>();
    	for(BusinessInCountry i: list) {
    		BusinessInCountryBean item = new BusinessInCountryBean();
    		
    		res.add(item);
    	}
    	
    	return res;
    }
    
    public List<BusinessInCountryBean> retrieveBusinesses(CountryBean country, BusinessInCountryBean bus){
    	BusinessInCountry offer = new BusinessInCountry();
    	List<BusinessInCountry> list = offer.getBusinessesInCountry();

    	/*DUMMY BEHAVIOR*/
    	List<BusinessInCountryBean> res = new ArrayList<>();
    	for(BusinessInCountry i: list) {
    		BusinessInCountryBean item = new BusinessInCountryBean();
    		
    		res.add(item);
    	}
    	
    	return res;
    }
}
