package logic.application.control;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.domain.Job;
import logic.domain.Offer;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;
import logic.presentation.bean.CountryBean;
import logic.presentation.bean.JobBean;
import logic.presentation.bean.OfferBean;
import logic.service.Entity;
import logic.service.Factory;
import logic.service.JobFactory;
import logic.service.OfferFactory;
import logic.service.Types;

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
      
    public List<JobBean> retrieveJobs() throws DatabaseFailureException {
    	Entity factory = Factory.getInstance().getObject(Types.JOB);
    	Job job = (Job)factory.createObject();
    	try {
    		List<Job> list = job.getAvailableJobs();
    		return ((JobFactory)factory).extractToBean(list);
		} catch (SQLException e) {
			Logger.getLogger(ViewOfferControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
	}
    
    public ObservableList<OfferBean> retrieveOffersByJob(JobBean bean) throws DatabaseFailureException, NoResultFoundException {
    	Entity factory = Factory.getInstance().getObject(Types.OFFER);
    	Offer offer = (Offer)factory.createObject();
		try {
			List<Offer> list = offer.getOffersByPosition(bean.getCategory());
			return modelToBean(list);
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			Logger.getLogger(ViewOfferControl.class.getName()).log(Level.SEVERE, null, se);
			throw new DatabaseFailureException();
		}
    }
    
    public ObservableList<OfferBean> retrieveOffersByCountry(CountryBean bean) throws DatabaseFailureException, NoResultFoundException {
    	Entity factory = Factory.getInstance().getObject(Types.OFFER);
    	Offer offer = (Offer)factory.createObject();
		try {
			List<Offer> list = offer.getOffersByPlace(bean.getName());
			return modelToBean(list);
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			Logger.getLogger(ViewOfferControl.class.getName()).log(Level.SEVERE, null, se);
			throw new DatabaseFailureException();
		}
    }
    
    public ObservableList<OfferBean> retrieveOffers(CountryBean country, JobBean job) throws DatabaseFailureException, NoResultFoundException{
    	Entity factory = Factory.getInstance().getObject(Types.OFFER);
    	Offer offer = (Offer)factory.createObject();
		try {
			List<Offer> list = offer.getOffers(country.getName(), job.getCategory());
			return modelToBean(list);
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			Logger.getLogger(ViewOfferControl.class.getName()).log(Level.SEVERE, null, se);
			throw new DatabaseFailureException();
		}
    }
    
    private ObservableList<OfferBean> modelToBean(List<Offer> src) {
    	Entity factory = Factory.getInstance().getObject(Types.OFFER);
    	
    	ObservableList<OfferBean> dest = FXCollections.observableArrayList();
    	for(OfferBean i: ((OfferFactory)factory).extractOfferBeanList(src)) {
    		dest.add(i);
    	}   	
    	return dest;
    }

	public OfferBean retrieveOfferById(Integer id) throws DatabaseFailureException {
		Entity factory = Factory.getInstance().getObject(Types.OFFER);

		try {
			Offer offer = ((Offer)factory.createObject()).getOffer(id);
			return ((OfferFactory)factory).extractOfferBean(offer);
		} catch (SQLException e) {
			Logger.getLogger(ViewOfferControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
	}

}
