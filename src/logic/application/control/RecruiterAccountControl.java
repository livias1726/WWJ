package logic.application.control;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.application.SessionFacade;
import logic.domain.Address;
import logic.domain.Company;
import logic.domain.Offer;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.bean.CompanyBean;
import logic.presentation.bean.OfferBean;
import logic.service.Entity;
import logic.service.AddressFactory;
import logic.service.CompanyFactory;
import logic.service.Factory;
import logic.service.OfferFactory;
import logic.service.Types;

public class RecruiterAccountControl {
	
	private static RecruiterAccountControl instance = null;

    private RecruiterAccountControl() {
    	/*Default constructor*/
    }

    public static RecruiterAccountControl getInstance() {
        if(instance == null) {
        	instance = new RecruiterAccountControl();
        }

        return instance;
    }

	public CompanyBean retrieveCompanyInfo() throws DatabaseFailureException {
		Entity factory = Factory.getInstance().getObject(Types.COMPANY);
    	Company comp = (Company)factory.createObject();
    	
		try {
			comp.getCompanyInfo(SessionFacade.getSession().getID());
			return ((CompanyFactory)factory).extractCompanyBean(comp);
		} catch (SQLException e) {
			Logger.getLogger(RecruiterAccountControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
	}

	public ObservableList<OfferBean> retrievePublishedOffers() throws DatabaseFailureException {
		Entity factory = Factory.getInstance().getObject(Types.OFFER);
    	Offer offer = (Offer)factory.createObject();
    	
		try {
			List<Offer> list = offer.getOffersByRecruiter(SessionFacade.getSession().getID());
			
			ObservableList<OfferBean> dest = FXCollections.observableArrayList();
			for(OfferBean i: ((OfferFactory)factory).extractPublishOfferBeanList(list)) {
				dest.add(i);
			}
			return dest;
		} catch (SQLException e) {
			Logger.getLogger(RecruiterAccountControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
	}

	public void changeCompanyInfo(CompanyBean bean) throws DatabaseFailureException {
		Entity factoryComp = Factory.getInstance().getObject(Types.COMPANY);
    	Company company = (Company)factoryComp.createObject();
		company.setName(bean.getName());
    	company.setDescription(bean.getDescription());
    
    	Entity factoryAddr = Factory.getInstance().getObject(Types.ADDRESS);
    	List<Address> branches = ((AddressFactory)factoryAddr).extractAddressList(bean.getBranches());	
    	company.setBranches(branches);
    	
		try {
			company.saveCompanyInfoOnDB(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			Logger.getLogger(RecruiterAccountControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
	}
}
