package logic.application.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.bean.AddressBean;
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
       
    public ObservableList<OfferBean> retrieveOffersByJob(JobBean bean) throws DatabaseFailureException, NoResultFoundException {
    	Offer offer = new Offer();
    	List<Offer> list = null;
		try {
			list = offer.getOffersByPosition(bean.getCategory());
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}
   
    	return modelToBean(list);
    }
    
    public ObservableList<OfferBean> retrieveOffersByCountry(CountryBean bean) throws DatabaseFailureException, NoResultFoundException {
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
    
    public ObservableList<OfferBean> retrieveOffers(CountryBean country, JobBean job) throws DatabaseFailureException, NoResultFoundException{
    	Offer offer = new Offer();
    	List<Offer> list = null;
		try {
			list = offer.getOffers(country.getName(), job.getCategory());
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}
    	   
    	return modelToBean(list);
    }
    
    private ObservableList<OfferBean> modelToBean(List<Offer> src) {
    	ObservableList<OfferBean> dest = FXCollections.observableArrayList();
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

	public OfferBean retrieveOfferById(Integer id) throws DatabaseFailureException {
		Offer offer;
		try {
			offer = new Offer().getOffer(id);
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
