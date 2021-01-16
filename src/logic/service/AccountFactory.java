package logic.service;

import logic.application.Users;
import logic.domain.Account;
import logic.presentation.bean.AccountBean;
import logic.presentation.bean.UserBean;

public class AccountFactory {
	
	private static AccountFactory instance = null;

    private AccountFactory() {
    	/*Default constructor*/
    }

    public static AccountFactory getInstance() {
        if(instance == null) {
        	instance = new AccountFactory();
        }

        return instance;
    }
    
    public Account createAccount() {
    	return new Account();
    }
    
    public AccountBean extractAccountBean(Account account) {
    	AccountBean bean = new AccountBean();
    	UserBean user = new UserBean();
    	
    	user.setFirstName(account.getUser().getFirstName());
    	user.setLastName(account.getUser().getLastName());
    	
    	bean.setUser(user);
    	bean.setType(Users.usersToString(account.getType()));	
    	bean.setPremium(account.isPremium());
    	bean.setPic(account.getPic());
    	bean.setId(account.getID());
  	
    	return bean;
    }
}
