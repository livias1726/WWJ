package logic.application.control;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import logic.application.SessionFacade;
import logic.application.Users;
import logic.bean.AccountBean;
import logic.bean.UserBean;
import logic.domain.Account;
import logic.exceptions.DatabaseFailureException;

public class AccountControl {
	
	private static AccountControl instance = null;

    private AccountControl() {
    	/*Default constructor*/
    }

    public static AccountControl getInstance() {
        if(instance == null) {
        	instance = new AccountControl();
        }

        return instance;
    }

    public AccountBean retrieveAccount() throws DatabaseFailureException{
    	Account account = new Account();
    	try {
			account.getAccountFromDB(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException("Something went wrong. Please, retry later.");
		}   	
    	return modelToBean(account);
    }
    
    private AccountBean modelToBean(Account account) {
    	AccountBean bean = new AccountBean();
    	UserBean user = new UserBean();
    	
    	user.setFirstName(account.getUser().getFirstName());
    	user.setLastName(account.getUser().getLastName());
    	
    	bean.setUser(user);
    	bean.setType(Users.usersToString(account.getType()));	
    	bean.setPremium(account.isPremium());
    	bean.setId(account.getID());
  	
    	return bean;
    }
    
    public UserBean retrievePersonalInfo() {
		return null;
	}
    
    public void updateAccount() {
    	/*Update personal info for the account*/
    }
    
    public void updateAccountPic(File img) {
    	/*Update image field for the account*/
    }

	public List<String> retrieveNotifications() throws DatabaseFailureException {
		Account account = new Account();
		try {
			return account.getNotificationsFromDB(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException("Something went wrong. Please, retry later.");
		}
	}	
}
