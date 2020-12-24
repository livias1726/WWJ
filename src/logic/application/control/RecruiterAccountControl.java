package logic.application.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.application.SessionFacade;
import logic.bean.AddressBean;
import logic.bean.CompanyBean;
import logic.bean.JobBean;
import logic.bean.OfferBean;
import logic.domain.Address;
import logic.domain.Company;
import logic.domain.Offer;
import logic.domain.RecruiterAccount;
import logic.exceptions.DatabaseFailureException;

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
		RecruiterAccount account = new RecruiterAccount();
    	Company company;
		try {
			company = account.getCompanyInfoFromDB(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
    	
    	return modelToBean(company);	
	}

	private CompanyBean modelToBean(Company company) {
		CompanyBean bean = new CompanyBean();
		bean.setName(company.getName());
		bean.setDescription(company.getDescription());
		
		AddressBean head = new AddressBean();
		head.setState(company.getHeadquarter().getState());
		head.setCity(company.getHeadquarter().getCity());
		head.setPostalCode(company.getHeadquarter().getPostalCode());
		head.setStreet(company.getHeadquarter().getStreet());
		head.setNumber(company.getHeadquarter().getNumber());
		
		bean.setHeadquarter(head);
		
		for(Address i: company.getBranches()) {
			AddressBean branch = new AddressBean();
			branch.setState(i.getState());
			branch.setCity(i.getCity());
			branch.setPostalCode(i.getPostalCode());
			branch.setStreet(i.getStreet());
			branch.setNumber(i.getNumber());
			
			bean.getBranches().add(branch);
		}
		
		return bean;
	}

	public List<OfferBean> retrievePublishedOffers() throws DatabaseFailureException {
		List<Offer> list = null;
		Offer offer = new Offer();
		
		try {
			list = offer.getOffersByRecruiter(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			Logger.getLogger(RecruiterAccountControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
		
		return modelToBean(list);
	}
	
	private List<OfferBean> modelToBean(List<Offer> src){
		List<OfferBean> dest = new ArrayList<>();
		for(Offer i: src) {
			OfferBean bean = new OfferBean();		
			bean.setId(i.getId());
			
			JobBean jobBean = new JobBean();
			jobBean.setName(i.getPosition().getName());
			bean.setPosition(jobBean);
			
			bean.setUpload(i.getUpload());
			bean.setExpiration(i.getExpiration());
			bean.setCandidates(i.getCandidates());
			
			dest.add(bean);
		}
		
		return dest;
	}
		
}
