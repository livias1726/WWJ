package logic.application.control;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.security.auth.login.FailedLoginException;

import logic.application.SessionFacade;
import logic.domain.Account;
import logic.domain.User;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.bean.AccountBean;
import logic.service.AccountFactory;

/**Singleton*/
public class LoginControl {
	
	private static LoginControl instance = null;
	
    private LoginControl() {
    	/*Default constructor*/
    }

    public static LoginControl getInstance() {
        if(instance == null) {
        	instance = new LoginControl();
        }

        return instance;
    }

    public void tryLogin(AccountBean bean) throws FailedLoginException, DatabaseFailureException{
    	Account account = AccountFactory.getInstance().createAccount();
    	User user = new User(bean.getUser().getEmail(), bean.getUser().getPassword());
    	account.setUser(user);
    	
		try {
			account.tryLoginToDB();
			
			SessionFacade.getSession().setID(account.getID());
    		SessionFacade.getSession().setCurrUserType(account.getType());
    		SessionFacade.getSession().setPremium(account.isPremium());
		
		} catch (SQLException se) {
			Logger.getLogger(CheckCandidatesControl.class.getName()).log(Level.SEVERE, null, se);
			throw new DatabaseFailureException();
		} catch (FailedLoginException fe) {
			throw new FailedLoginException("Login failed. Please, check your credentials or Sign Up.");
		}

    }
}
