package logic.application.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.application.SessionFacade;
import logic.bean.AddressBean;
import logic.bean.CompanyBean;
import logic.bean.CountryBean;
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
    	
		CompanyBean bean = new CompanyBean();
		bean.setName(company.getName());
		bean.setDescription(company.getDescription());

		bean.setHeadquarter(extractAddressBean(company.getHeadquarter()));
		
		List<AddressBean> branches = new ArrayList<>();
		for(Address i: company.getBranches()) {
			branches.add(extractAddressBean(i));
		}
		
		return bean;
	}

	private AddressBean extractAddressBean(Address ent) {
		AddressBean bean = new AddressBean();
		
		CountryBean country = new CountryBean();
		country.setName(ent.getCountry().getName());
		
		bean.setCountry(country);
		bean.setState(ent.getState());
		bean.setCity(ent.getCity());
		bean.setPostalCode(ent.getPostalCode());
		bean.setStreet(ent.getStreet());
		bean.setNumber(ent.getNumber());
		
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
		
		List<OfferBean> dest = new ArrayList<>();
		for(Offer i: list) {
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

	public void changeCompanyInfo() throws DatabaseFailureException {
		Company company = new Company();
    	
		try {
			company.saveCompanyInfoOnDB(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}
		
}
