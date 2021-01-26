package logic.application.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.application.SessionFacade;
import logic.domain.Account;
import logic.domain.User;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.bean.AccountBean;
import logic.presentation.bean.UserBean;
import logic.service.Entity;
import logic.service.AccountFactory;
import logic.service.Factory;
import logic.service.Types;
import logic.service.UserFactory;

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
    	Entity factory = Factory.getInstance().getObject(Types.ACCOUNT);
		Account account = (Account)factory.createObject();
		account.setID(SessionFacade.getSession().getID().intValue());
    	try {
			account.getAccountFromDB();
			return ((AccountFactory)factory).extractAccountBean(account);
		} catch (SQLException | IOException e) {
			Logger.getLogger(ManageAccountControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		} 
    }
    
    public AccountBean retrieveAccount(Long accountID) throws DatabaseFailureException {
    	Entity factory = Factory.getInstance().getObject(Types.ACCOUNT);
		Account account = (Account)factory.createObject();
		account.setID(accountID.intValue());
    	try {
			account.getAccountFromDB();
			return ((AccountFactory)factory).extractAccountBean(account);
		} catch (SQLException | IOException e) {
			Logger.getLogger(ManageAccountControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}  
	}	
    
    public UserBean retrievePersonalInfo(Long id) throws DatabaseFailureException {    	
    	Entity factory = Factory.getInstance().getObject(Types.USER);
		User user = (User)factory.createObject();
		
		try {
			user.getPersonalInfoFromDB(id);
	    	return ((UserFactory)factory).extractUserBean(user);
		} catch (SQLException e) {
			Logger.getLogger(ManageAccountControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
    
	}
    
    public void updateAccountPic(AccountBean account) throws DatabaseFailureException {
    	Entity factory = Factory.getInstance().getObject(Types.ACCOUNT);
		Account acc = (Account)factory.createObject();
    	try {
			acc.savePic(account.getPic(), SessionFacade.getSession().getID());
		} catch (SQLException | IOException e) {
			Logger.getLogger(ManageAccountControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException(); 
		}
    }

	public List<String> retrieveNotifications() throws DatabaseFailureException {
		Entity factory = Factory.getInstance().getObject(Types.ACCOUNT);
		Account account = (Account)factory.createObject();
		try {
			return account.getNotificationsFromDB(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			Logger.getLogger(ManageAccountControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
	}

	public void changePersonalInfo(UserBean bean) throws DatabaseFailureException {
    	Entity factoryUs = Factory.getInstance().getObject(Types.USER);
		User user = ((UserFactory)factoryUs).extractUser(bean);
		try {
			user.savePersonalInfoOnDB(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			Logger.getLogger(ManageAccountControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
	}

}
