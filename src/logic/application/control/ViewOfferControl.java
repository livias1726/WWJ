package logic.application.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.AddressBean;
import logic.bean.CountryBean;
import logic.bean.JobBean;
import logic.bean.OfferBean;
import logic.domain.Country;
import logic.domain.Job;
import logic.domain.Offer;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;

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
    	Offer offer = new Offer();
    	List<Offer> list = null;
		try {
			list = offer.getOffersByPosition(bean.getName());
		} catch (NoResultFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}
   
    	return modelToBean(list);
    }
    
    public List<OfferBean> retrieveOffersByCountry(CountryBean bean) {
    	Offer offer = new Offer();
    	List<Offer> list = null;
		try {
			list = offer.getOffersByPlace(bean.getName());
		} catch (NoResultFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}
    	   
    	return modelToBean(list);
    }
    
    public List<OfferBean> retrieveOffers(CountryBean country, JobBean job){
    	Offer offer = new Offer();
    	List<Offer> list = null;
		try {
			list = offer.getOffers(country.getName(), job.getName());
		} catch (NoResultFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}
    	   
    	return modelToBean(list);
    }
    
    private List<OfferBean> modelToBean(List<Offer> src) {
    	List<OfferBean> dest = new ArrayList<>();
    	for(Offer i: src) {
    		OfferBean offerBean = new OfferBean();
    		
    		offerBean.setCompanyName(i.getCompanyName());
    		offerBean.setStart(i.getStart());
    		offerBean.setFinish(i.getFinish());
    		offerBean.setTaskDescription(i.getTaskDescription());
    		offerBean.setBaseSalary(i.getBaseSalary());
    		offerBean.setExpiration(i.getExpiration());
    		
    		offerBean.setRequirements(i.getRequirements());
    	
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

	public OfferBean retrieveOfferById(Integer id) throws DatabaseFailureException {
		Offer offer = new Offer();
		try {
			offer = offer.getOffer(id);
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
		
		return modelToBean(offer);
	}

	private OfferBean modelToBean(Offer offer) {
		OfferBean bean = new OfferBean();
		
		bean.setCompanyName(offer.getCompanyName());
    	
    	JobBean job = new JobBean();
    	job.setName(offer.getPosition().getName());
    	bean.setPosition(job);
    	
    	bean.setTaskDescription(offer.getTaskDescription());
    	
    	AddressBean branch = new AddressBean();
    	branch.setState(offer.getBranch().getState());
    	branch.setCity(offer.getBranch().getCity());
    	branch.setPostalCode(offer.getBranch().getPostalCode());
    	branch.setStreet(offer.getBranch().getStreet());
    	branch.setNumber(offer.getBranch().getNumber());
    	bean.setBranch(branch);
    	
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

}
