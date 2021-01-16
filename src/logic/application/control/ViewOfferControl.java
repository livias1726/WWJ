package logic.application.control;

import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.domain.Job;
import logic.domain.Offer;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;
import logic.presentation.bean.CountryBean;
import logic.presentation.bean.JobBean;
import logic.presentation.bean.OfferBean;
import logic.service.JobFactory;
import logic.service.OfferFactory;

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
    	try {
    		List<Job> list = JobFactory.getInstance().createJob().getAvailableJobs();
    		return JobFactory.getInstance().extractToBean(list);
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}
    
    public ObservableList<OfferBean> retrieveOffersByJob(JobBean bean) throws DatabaseFailureException, NoResultFoundException {
		try {
			List<Offer> list = OfferFactory.getInstance().createOffer().getOffersByPosition(bean.getCategory());
			return modelToBean(list);
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}
    }
    
    public ObservableList<OfferBean> retrieveOffersByCountry(CountryBean bean) throws DatabaseFailureException, NoResultFoundException {
		try {
			List<Offer> list = OfferFactory.getInstance().createOffer().getOffersByPlace(bean.getName());
			return modelToBean(list);
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}
    }
    
    public ObservableList<OfferBean> retrieveOffers(CountryBean country, JobBean job) throws DatabaseFailureException, NoResultFoundException{
		try {
			List<Offer> list = OfferFactory.getInstance().createOffer().getOffers(country.getName(), job.getCategory());
			return modelToBean(list);
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException();
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}
    }
    
    private ObservableList<OfferBean> modelToBean(List<Offer> src) {
    	ObservableList<OfferBean> dest = FXCollections.observableArrayList();
    	for(OfferBean i: OfferFactory.getInstance().extractOfferBeanList(src)) {
    		dest.add(i);
    	}   	
    	return dest;
    }

	public OfferBean retrieveOfferById(Integer id) throws DatabaseFailureException {
		try {
			Offer offer = OfferFactory.getInstance().createOffer().getOffer(id);
			return OfferFactory.getInstance().extractOfferBean(offer);
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}

}
