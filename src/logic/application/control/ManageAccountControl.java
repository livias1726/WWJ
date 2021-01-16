package logic.application.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.application.SessionFacade;
import logic.domain.Account;
import logic.domain.User;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.bean.AccountBean;
import logic.presentation.bean.UserBean;
import logic.service.AccountFactory;

public class ManageAccountControl {
	
	private static ManageAccountControl instance = null;

    private ManageAccountControl() {
    	/*Singleton*/
    }

    public static ManageAccountControl getInstance() {
        if(instance == null) {
        	instance = new ManageAccountControl();
        }

        return instance;
    }

    public AccountBean retrieveAccount() throws DatabaseFailureException{
    	try {
			Account account = AccountFactory.getInstance().createAccount().getAccountFromDB(SessionFacade.getSession().getID());
			return AccountFactory.getInstance().extractAccountBean(account);
		} catch (SQLException | IOException e) {
			throw new DatabaseFailureException();
		} 
    }
    
    public AccountBean retrieveAccount(Long accountID) throws DatabaseFailureException {
    	try {
			Account account = AccountFactory.getInstance().createAccount().getAccountFromDB(accountID);
			return AccountFactory.getInstance().extractAccountBean(account);
		} catch (SQLException | IOException e) {
			throw new DatabaseFailureException();
		}  
	}	
    
    public UserBean retrievePersonalInfo(Long id) throws DatabaseFailureException {    	
    	User user;
		try {
			user = new User().getPersonalInfoFromDB(id);
			
			UserBean bean = new UserBean();
	    	bean.setEmail(user.getEmail());
	    	bean.setPassword(user.getPwd());
	    	bean.setFirstName(user.getFirstName());
	    	bean.setLastName(user.getLastName());
	    	bean.setCity(user.getCity());
	    	bean.setBirth(user.getBirth());
	    	
	    	return bean;
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
    
	}
    
    public void updateAccountPic(AccountBean account) throws DatabaseFailureException {
    	try {
    		AccountFactory.getInstance().createAccount().savePic(account.getPic(), SessionFacade.getSession().getID());
		} catch (SQLException | IOException e) {
			throw new DatabaseFailureException(); 
		}
    }

	public List<String> retrieveNotifications() throws DatabaseFailureException {
		try {
			return AccountFactory.getInstance().createAccount().getNotificationsFromDB(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}

	public void changePersonalInfo(UserBean bean) throws DatabaseFailureException {
    	User user = new User(bean.getEmail(), bean.getPassword(), bean.getFirstName(), bean.getLastName());
    	user.setCity(bean.getCity());
    	user.setBirth(bean.getBirth());
		try {
			user.savePersonalInfoOnDB(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}

}
