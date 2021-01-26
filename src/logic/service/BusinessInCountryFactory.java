package logic.service;

import java.util.ArrayList;
import java.util.List;

import logic.domain.BusinessInCountry;
import logic.presentation.bean.BusinessInCountryBean;
import logic.presentation.bean.CountryBean;

public class BusinessInCountryFactory implements Entity{

	@Override
    public BusinessInCountry createObject() {
    	return new BusinessInCountry();
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
}
