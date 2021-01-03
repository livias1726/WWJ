package logic.application.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.application.SessionFacade;
import logic.bean.AddressBean;
import logic.bean.CompanyBean;
import logic.bean.CountryBean;
import logic.bean.JobBean;
import logic.bean.OfferBean;
import logic.domain.Address;
import logic.domain.Company;
import logic.domain.Country;
import logic.domain.Offer;
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
		Company company;
		
		try {
			company = new Company().getCompanyInfoFromDB(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
    	
		if(company != null) {
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
		
		return null;
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

	public ObservableList<OfferBean> retrievePublishedOffers() throws DatabaseFailureException {
		List<Offer> list = null;
		Offer offer = new Offer();
		
		try {
			list = offer.getOffersByRecruiter(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			Logger.getLogger(RecruiterAccountControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
		
		ObservableList<OfferBean> dest = FXCollections.observableArrayList();
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

	public void changeCompanyInfo(CompanyBean bean) throws DatabaseFailureException {
		Company company = new Company(bean.getName());
    	company.setDescription(bean.getDescription());
    	
    	if(bean.getHeadquarter() != null) {
    		company.setHeadquarter(extractAddress(bean.getHeadquarter()));
    	}

    	List<Address> branches = new ArrayList<>();
    	if(bean.getBranches() != null) {
    		for(AddressBean i: bean.getBranches()) {
        		branches.add(extractAddress(i));
        	}
    	}
    	
    	company.setBranches(branches);
    	
		try {
			company.saveCompanyInfoOnDB(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}

	private Address extractAddress(AddressBean bean) {
		Address addr = new Address();
		
		Country country = new Country();
		country.setName(bean.getCountry().getName());
		
		addr.setCountry(country);
		addr.setState(bean.getState());
		addr.setCity(bean.getCity());
		addr.setPostalCode(bean.getPostalCode());
		addr.setStreet(bean.getStreet());
		addr.setNumber(bean.getNumber());
		
		return addr;
	}
		
}
