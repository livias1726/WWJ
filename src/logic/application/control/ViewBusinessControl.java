package logic.application.control;

import java.util.ArrayList;
import java.util.List;

import logic.bean.BusinessInCountryBean;
import logic.bean.CountryBean;
import logic.domain.Business;
import logic.domain.BusinessInCountry;
import logic.domain.Country;

public class ViewBusinessControl {
	
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
    
    public List<String> retrieveCountries(){  	
    	Country country = new Country();
    	
    	return country.getAvailableCountries();
    }
    
    public List<String> retrieveBusinesses(){
    	
		Business business = new Business();
    	return business.getAvailableBusinesses();
    }
    
    public List<BusinessInCountryBean> retrieveBusinessesByName(BusinessInCountryBean bean) {
    	BusinessInCountry business = new BusinessInCountry();
    	List<BusinessInCountry> list = business.getBusinessesByName();
   
    	//return extractOffer(list);
    	
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
    	   
    	//return extractOffer(list);
    	
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
    	   
    	//return extractOffer(list);
    	
    	/*DUMMY BEHAVIOR*/
    	List<BusinessInCountryBean> res = new ArrayList<>();
    	for(BusinessInCountry i: list) {
    		BusinessInCountryBean item = new BusinessInCountryBean();
    		
    		res.add(item);
    	}
    	
    	return res;
    }
}
