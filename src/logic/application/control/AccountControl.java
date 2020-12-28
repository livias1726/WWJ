package logic.application.control;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import logic.application.SessionFacade;
import logic.application.Users;
import logic.bean.AccountBean;
import logic.bean.UserBean;
import logic.domain.Account;
import logic.domain.User;
import logic.exceptions.DatabaseFailureException;

public class AccountControl {
	
	private static AccountControl instance = null;

    private AccountControl() {
    	/*Singleton*/
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
			account = account.getAccountFromDB(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
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
    
    public UserBean retrievePersonalInfo() throws DatabaseFailureException {
    	
    	User user = new User();
		try {
			user = user.getPersonalInfoFromDB(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
    	
    	return modelToBean(user);	
	}
    
    private UserBean modelToBean(User user) {
    	UserBean bean = new UserBean(user.getEmail(), user.getPwd());
    	bean.setFirstName(user.getFirstName());
    	bean.setLastName(user.getLastName());
    	bean.setCity(user.getCity());
    	bean.setBirth(user.getBirth());
 
    	return bean;
    }
    
    public void updateAccountPic(File img) {
    	/*Update image field for the account*/
    }

	public List<String> retrieveNotifications() throws DatabaseFailureException {
		Account account = new Account();
		try {
			return account.getNotificationsFromDB(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}

	public void changePersonalInfo(UserBean bean) throws DatabaseFailureException {
    	User user = new User(bean.getFirstName(), bean.getLastName(), bean.getEmail(), bean.getPassword());
    	user.setCity(bean.getCity());
    	user.setBirth(bean.getBirth());
		try {
			user.savePersonalInfoOnDB(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}	
}
