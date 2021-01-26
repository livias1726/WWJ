package logic.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import logic.domain.Address;
import logic.domain.Job;
import logic.domain.Offer;
import logic.presentation.bean.AddressBean;
import logic.presentation.bean.CountryBean;
import logic.presentation.bean.JobBean;
import logic.presentation.bean.OfferBean;

public class OfferFactory implements Entity{
    
	@Override
    public Offer createObject() {
    	return new Offer();
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
    		
    		offerBean.setUpload(i.getUpload().toString());
    		offerBean.setExpiration(i.getExpiration().toString());
    		
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
			
			bean.setUpload(i.getUpload().toString());
			bean.setExpiration(i.getExpiration().toString());
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
    	
    	bean.setBranch((new AddressFactory()).extractAddressBean(offer.getBranch()));	
    	
    	bean.setStart(offer.getStart().toString());
    	bean.setFinish(offer.getFinish().toString());
    	bean.setBaseSalary(offer.getBaseSalary());
    	bean.setExpiration(offer.getExpiration().toString());
    	
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
    	
    	offer.setStart(LocalTime.parse(bean.getStart(), DateTimeFormatter.ofPattern("H[:mm]")));
    	offer.setFinish(LocalTime.parse(bean.getFinish(), DateTimeFormatter.ofPattern("H[:mm]")));
    	offer.setBaseSalary(bean.getBaseSalary());
    	offer.setExpiration(LocalDate.parse(bean.getExpiration(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    	
    	List<String> requirements = new ArrayList<>();
    	for(String i: bean.getRequirements()) {
    		requirements.add(i);
    	}		
		offer.setRequirements(requirements);
		
		return offer;
    }
    
}
