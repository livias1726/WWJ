package logic.service;

import java.util.ArrayList;
import java.util.List;

import logic.domain.Address;
import logic.domain.Job;
import logic.domain.Offer;
import logic.presentation.bean.AddressBean;
import logic.presentation.bean.CountryBean;
import logic.presentation.bean.JobBean;
import logic.presentation.bean.OfferBean;

public class OfferFactory {
	
	private static OfferFactory instance = null;

    private OfferFactory() {
    	/*Default constructor*/
    }

    public static OfferFactory getInstance() {
        if(instance == null) {
        	instance = new OfferFactory();
        }

        return instance;
    }
    
    public Offer createOffer() {
    	return new Offer();
    }
    
    public Offer createOffer(int id) {
    	return new Offer(id);
    }
    
    public List<OfferBean> extractOfferBeanList(List<Offer> src){
    	List<OfferBean> dest = new ArrayList<>();
    	
    	for(Offer i: src) {
    		OfferBean offerBean = new OfferBean();  
    		offerBean.setId(i.getId());
    		offerBean.setCompanyName(i.getCompanyName());

    		JobBean job = new JobBean();
    		job.setName(i.getPosition().getName());
    		job.setCategory(i.getPosition().getCategory());  		
    		offerBean.setPosition(job);
    		
    		AddressBean branch = new AddressBean();
    		CountryBean country = new CountryBean();
    		country.setName(i.getBranch().getCountry().getName());
    		branch.setCountry(country);
    		branch.setState(i.getBranch().getState());
    		branch.setCity(i.getBranch().getCity());
    		offerBean.setBranch(branch);
    		
    		offerBean.setUpload(i.getUpload());
    		offerBean.setExpiration(i.getExpiration());
    		
    		dest.add(offerBean);
    	}
    	
    	return dest;
    }
    
    public List<OfferBean> extractPublishOfferBeanList(List<Offer> src){
    	List<OfferBean> dest = new ArrayList<>();
    	
    	for(Offer i: src) {
			OfferBean bean = new OfferBean();		
			bean.setId(i.getId());
			
			JobBean jobBean = new JobBean();
			jobBean.setName(i.getPosition().getName());
			bean.setPosition(jobBean);
			
			bean.setUpload(i.getUpload());
			bean.setExpiration(i.getExpiration());
			bean.setCandidates(i.getCandidates());
			
			dest.add(bean);
		}
		
		return dest;
    }
    
    public OfferBean extractOfferBean(Offer offer){
    	OfferBean bean = new OfferBean();
		
		bean.setCompanyName(offer.getCompanyName());
    	
    	JobBean job = new JobBean();
    	job.setName(offer.getPosition().getName());
    	bean.setPosition(job);
    	
    	bean.setTaskDescription(offer.getTaskDescription());
    	bean.setBranch(AddressFactory.getInstance().extractAddressBean(offer.getBranch()));	
    	bean.setStart(offer.getStart());
    	bean.setFinish(offer.getFinish());
    	bean.setBaseSalary(offer.getBaseSalary());
    	bean.setExpiration(offer.getExpiration());
    	
    	List<String> requirements = new ArrayList<>();
    	for(String i: offer.getRequirements()) {
    		requirements.add(i);
    	}
		
		bean.setRequirements(requirements);
		return bean;
    }
    
    public Offer extractOffer(OfferBean bean){
    	Offer offer = new Offer();
		
    	Job job = new Job();
    	job.setId(bean.getPosition().getId());
    	offer.setPosition(job);
    	
    	offer.setTaskDescription(bean.getTaskDescription());
    	
    	Address branch = new Address();
    	branch.setId(bean.getBranch().getId());
    	offer.setBranch(branch);
    	
    	offer.setStart(bean.getStart());
    	offer.setFinish(bean.getFinish());
    	offer.setBaseSalary(bean.getBaseSalary());
    	offer.setExpiration(bean.getExpiration());
    	
    	List<String> requirements = new ArrayList<>();
    	for(String i: bean.getRequirements()) {
    		requirements.add(i);
    	}		
		offer.setRequirements(requirements);
		
		return offer;
    }
    
}
