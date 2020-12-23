package logic.application.control;

import java.sql.SQLException;

import logic.application.SessionFacade;
import logic.bean.AddressBean;
import logic.bean.CompanyBean;
import logic.bean.RecruiterAccountBean;
import logic.domain.Address;
import logic.domain.Company;
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
    
    /*DUMMY*/
    public RecruiterAccountBean retrieveRecAccount() {
    	/*
    	 * Access the DB and deserialize the account info related to the id
    	 */
        return new RecruiterAccountBean();
    }
    
    /*DUMMY*/
    public void updateCompany(RecruiterAccountBean bean){
    	/*
    	 * Access the DB and update info
    	 */   	
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
   
}
