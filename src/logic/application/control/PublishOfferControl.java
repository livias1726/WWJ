package logic.application.control;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.application.SessionFacade;
import logic.domain.Company;
import logic.domain.Job;
import logic.domain.Offer;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.IncompleteAccountException;
import logic.presentation.bean.AddressBean;
import logic.presentation.bean.JobBean;
import logic.presentation.bean.OfferBean;
import logic.service.Entity;
import logic.service.AddressFactory;
import logic.service.Factory;
import logic.service.JobFactory;
import logic.service.OfferFactory;
import logic.service.Types;

public class PublishOfferControl {
	
	private static PublishOfferControl instance = null;
	
    private PublishOfferControl() {
    	/*Default constructor*/
    }

    public static PublishOfferControl getInstance() {
        if(instance == null) {
        	instance = new PublishOfferControl();
        }

        return instance;
    }
    
	public List<AddressBean> retrieveCompanyInfo() throws DatabaseFailureException, IncompleteAccountException {
		Entity factoryComp = Factory.getInstance().getObject(Types.COMPANY);
    	Company comp = (Company)factoryComp.createObject();
    	
    	Entity factoryAddr = Factory.getInstance().getObject(Types.ADDRESS);		
		try {
			comp.getCompanyInfo(SessionFacade.getSession().getID());
			if(comp.getBranches() == null) {
				throw new IncompleteAccountException("Your Company info is still incomplete. Please, complete the section before publishing an offer");
			}
			
			return ((AddressFactory)factoryAddr).extractAddressBeanList(comp.getBranches());	
		} catch (SQLException e) {
			Logger.getLogger(PublishOfferControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
	}
	
	public List<JobBean> retrieveJobs() throws DatabaseFailureException {
		Entity factory = Factory.getInstance().getObject(Types.JOB);
    	Job job = (Job)factory.createObject();
    	
    	try {
    		List<Job> list =job.getAvailableJobs();
    		return ((JobFactory)factory).extractToBean(list);
		} catch (SQLException e) {
			Logger.getLogger(PublishOfferControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
	}
	    
    public void saveNewJob(JobBean bean) throws DatabaseFailureException {
    	Entity factory = Factory.getInstance().getObject(Types.JOB);
    	Job job = (Job)factory.createObject();
    	
		job.setName(bean.getName());
		job.setCategory(bean.getCategory());
		try {
			job.addJobToDB();
		} catch (SQLException e) {
			Logger.getLogger(PublishOfferControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
	}

	public void publishNewOffer(OfferBean bean) throws DatabaseFailureException{
		Entity factory = Factory.getInstance().getObject(Types.OFFER);
		Offer offer = ((OfferFactory)factory).extractOffer(bean);
		try {
			offer.publish(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			Logger.getLogger(PublishOfferControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
	}
}
