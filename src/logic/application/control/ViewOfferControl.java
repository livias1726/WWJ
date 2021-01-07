package logic.application.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.CountryBean;
import logic.bean.JobBean;
import logic.bean.OfferBean;
import logic.domain.Offer;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;

public class ViewOfferControl extends ViewResultsControl{
	
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
       
    public List<OfferBean> retrieveOffersByJob(JobBean bean) throws DatabaseFailureException, NoResultFoundException {
    	Offer offer = new Offer();
    	List<Offer> list = null;
		try {
			list = offer.getOffersByPosition(bean.getName());
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}
   
    	return modelToBean(list);
    }
    
    public List<OfferBean> retrieveOffersByCountry(CountryBean bean) throws DatabaseFailureException, NoResultFoundException {
    	Offer offer = new Offer();
    	List<Offer> list = null;
		try {
			list = offer.getOffersByPlace(bean.getName());
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}
    	   
    	return modelToBean(list);
    }
    
    public List<OfferBean> retrieveOffers(CountryBean country, JobBean job) throws DatabaseFailureException, NoResultFoundException{
    	Offer offer = new Offer();
    	List<Offer> list = null;
		try {
			list = offer.getOffers(country.getName(), job.getName());
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}
    	   
    	return modelToBean(list);
    }
    
    private List<OfferBean> modelToBean(List<Offer> src) {
    	List<OfferBean> dest = new ArrayList<>();
    	for(Offer i: src) {
    		OfferBean offerBean = new OfferBean();  		
    		offerBean.setCompanyName(i.getCompanyName());
    		offerBean.setTaskDescription(i.getTaskDescription());

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
		
		OfferBean bean = new OfferBean();
		
		bean.setCompanyName(offer.getCompanyName());
    	
    	JobBean job = new JobBean();
    	job.setName(offer.getPosition().getName());
    	bean.setPosition(job);
    	
    	bean.setTaskDescription(offer.getTaskDescription());
    	bean.setBranch(AddressControl.getInstance().extractAddressBean(offer.getBranch()));	
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
