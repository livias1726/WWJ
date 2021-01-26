package logic.service;

import java.util.ArrayList;
import java.util.List;

import logic.domain.Business;
import logic.presentation.bean.BusinessBean;

public class BusinessFactory implements Entity {

	@Override
    public Business createObject() {
    	return new Business();
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
