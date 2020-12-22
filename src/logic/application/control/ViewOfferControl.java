package logic.application.control;

import java.util.ArrayList;
import java.util.List;

import logic.bean.AddressBean;
import logic.bean.CountryBean;
import logic.bean.EducationBean;
import logic.bean.ExperienceBean;
import logic.bean.JobBean;
import logic.bean.LanguageBean;
import logic.bean.OfferBean;
import logic.bean.RequirementBean;
import logic.domain.Country;
import logic.domain.Education;
import logic.domain.Experience;
import logic.domain.Job;
import logic.domain.Language;
import logic.domain.Offer;
import logic.domain.Requirement;

public class ViewOfferControl {
	
	private static ViewOfferControl instance = null;

    private ViewOfferControl() {
    	/*Default constructor*/
    }

    public static ViewOfferControl getInstance() {
        if(instance == null) {
        	instance = new ViewOfferControl();
        }

        return instance;
    }
    
    public List<String> retrieveCountries(){  	
    	Country country = new Country();
    	
    	return country.getAvailableCountries();
    }
    
    public List<String> retrieveJobs(){  	
    	Job job = new Job();
    	
    	return job.getAvailableJobs();
    }
    
    public List<OfferBean> retrieveOffersByJob(JobBean bean) {
    	Offer offer = new Offer(null, bean.getName());
    	List<Offer> list = offer.getOffersByPosition();
   
    	return extractOffer(list);
    }
    
    public List<OfferBean> retrieveOffersByCountry(CountryBean bean) {
    	Offer offer = new Offer(bean.getName(), null);
    	List<Offer> list = offer.getOffersByPlace();
    	   
    	return extractOffer(list);
    }
    
    public List<OfferBean> retrieveOffers(CountryBean country, JobBean job){
    	Offer offer = new Offer(country.getName(), job.getName());
    	List<Offer> list = offer.getOffers();
    	   
    	return extractOffer(list);
    }
    
    private List<OfferBean> extractOffer(List<Offer> src) {
    	List<OfferBean> dest = new ArrayList<>();
    	for(Offer i: src) {
    		OfferBean offerBean = new OfferBean();
    		offerBean.setCompanyName(i.getCompanyName());
    		offerBean.setWorkingTimeSlot(i.getWorkingTimeSlot());
    		offerBean.setTaskDescription(i.getTaskDescription());
    		offerBean.setBaseSalary(i.getBaseSalary());
    		offerBean.setExpiration(i.getExpiration());
    		
    		List<Requirement> req = i.getRequirements();
    		List<RequirementBean> reqBean = extractRequirement(req);
    		offerBean.setRequirements(reqBean);
    	
    		AddressBean address = new AddressBean();
    		address.setCity(i.getBranch().getCity());
    		address.setNumber(i.getBranch().getNumber());
    		address.setPostalCode(i.getBranch().getPostalCode());
    		address.setState(i.getBranch().getState());
    		address.setStreet(i.getBranch().getStreet());    		
    		offerBean.setBranch(address);
    		
    		JobBean job = new JobBean();
    		job.setName(i.getPosition().getName());
    		job.setCategory(i.getPosition().getCategory());  		
    		offerBean.setPosition(job);
    		
    		dest.add(offerBean);
    	}
    	
    	return dest;
    }
    
    private List<RequirementBean> extractRequirement(List<Requirement> src) {
    	List<RequirementBean> dest = new ArrayList<>();
    	for(Requirement i: src) {
    		RequirementBean reqBean = new RequirementBean();
    		
    		List<Language> lang = i.getLang();
    		List<LanguageBean> langBean = extractLanguage(lang); 	
    		reqBean.setLang(langBean);
    		
    		List<Education> edu = i.getEdu();
    		List<EducationBean> eduBean = extractEducation(edu);
    		reqBean.setEdu(eduBean);
    		
    		List<Experience> exp = i.getExp();
    		List<ExperienceBean> expBean = extractExperience(exp);
    		reqBean.setExp(expBean);
    		
    		dest.add(reqBean);
    	}
    	
    	return dest;
    }
    
    private List<LanguageBean> extractLanguage(List<Language> src) {
    	List<LanguageBean> dest = new ArrayList<>();
    	for(Language i: src) {
    		LanguageBean bean = new LanguageBean();
    		bean.setLang(i.getLang());
    		dest.add(bean);
    	}
    	
    	return dest;
    }
    
    private List<EducationBean> extractEducation(List<Education> src) {
    	List<EducationBean> dest = new ArrayList<>();
    	for(Education i: src) {
    		EducationBean bean = new EducationBean();
    		bean.setSubject(i.getSubject());
    		bean.setGrade(i.getGrade());
    		bean.setType(i.getType().toString());
    		
    		dest.add(bean);
    	}
    	
    	return dest;
    }
    
    private List<ExperienceBean> extractExperience(List<Experience> src) {
    	List<ExperienceBean> dest = new ArrayList<>();
    	for(Experience i: src) {
    		ExperienceBean bean = new ExperienceBean();
    		bean.setNumYears(i.getNumYears());
    		
    		JobBean job = new JobBean();
    		job.setName(i.getJob().getName());
    		job.setCategory(i.getJob().getCategory());  		
    		bean.setJob(job);
    		
    		dest.add(bean);
    	}
    	
    	return dest;
    }

}
