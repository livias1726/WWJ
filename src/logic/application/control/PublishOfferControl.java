package logic.application.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.application.SessionFacade;
import logic.domain.Address;
import logic.domain.Company;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.IncompleteAccountException;
import logic.presentation.bean.AddressBean;
import logic.presentation.bean.OfferBean;

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
				throw new IncompleteAccountException("Your Company info is still incomplete. Please, complete the section before publishing an offer");
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
		try {
			OfferFactory.getInstance().extractOffer(bean).publish(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}
}
