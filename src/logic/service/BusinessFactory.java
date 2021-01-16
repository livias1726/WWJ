package logic.service;

import java.util.ArrayList;
import java.util.List;

import logic.domain.Business;
import logic.domain.BusinessInCountry;
import logic.presentation.bean.BusinessBean;
import logic.presentation.bean.BusinessInCountryBean;
import logic.presentation.bean.CountryBean;

public class BusinessFactory {
	
	private static BusinessFactory instance = null;

    private BusinessFactory() {
    	/*Default constructor*/
    }

    public static BusinessFactory getInstance() {
        if(instance == null) {
        	instance = new BusinessFactory();
        }

        return instance;
    }
    
    public BusinessInCountry createBusiness() {
    	return new BusinessInCountry();
    }
    
    public BusinessInCountry createBusiness(int id) {
    	return new BusinessInCountry(id);
    }
    
    public List<BusinessInCountryBean> extractBusinessInCountryBeanList(List<BusinessInCountry> src) {
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
    
    public List<BusinessBean> extractBusinessBeanList(List<Business> src) {
    	List<BusinessBean> dest = new ArrayList<>();
    	for(Business i: src) {
    		BusinessBean bean = new BusinessBean();
    		
    		bean.setId(i.getId());
    		bean.setName(i.getName());
    		bean.setCategory(i.getCategory());
    		
    		dest.add(bean);
    	}
    	return dest;
    }
    
}
