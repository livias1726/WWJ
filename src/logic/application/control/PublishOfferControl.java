package logic.application.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.application.SessionFacade;
import logic.bean.AddressBean;
import logic.bean.OfferBean;
import logic.domain.Address;
import logic.domain.Company;
import logic.domain.Job;
import logic.domain.Offer;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.IncompleteAccountException;

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
    	Company comp = new Company();
    	List<AddressBean> bean = new ArrayList<>();
		
		try {
			comp = comp.getCompanyInfo(SessionFacade.getSession().getID());
			if(comp == null || comp.getBranches() == null) {
				throw new IncompleteAccountException("Your Company info is still not complete. Please, complete this section before publishing an offer");
			}
			
			for(Address i: comp.getBranches()) {
				bean.add(AddressControl.getInstance().extractAddressBean(i));
			}		
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
		
		return bean;
	}

	public void publishNewOffer(OfferBean bean) throws DatabaseFailureException{

		Offer offer = new Offer();
		
    	Job job = new Job();
    	job.setName(bean.getPosition().getName());
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
		
		try {
			offer.publish(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}
}
