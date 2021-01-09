package logic.application.control;

import java.sql.SQLException;

import javax.security.auth.login.FailedLoginException;

import logic.application.SessionFacade;
import logic.domain.Account;
import logic.domain.User;
import logic.exceptions.DatabaseFailureException;

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

    public void tryLogin(String email, String password) throws FailedLoginException, DatabaseFailureException{
        	
    	User user = new User(email, password);
    	Account account = new Account();
    	account.setUser(user);
    	
		try {
			account = account.tryLoginToDB();
			
			SessionFacade.getSession().setID(account.getID());
    		SessionFacade.getSession().setCurrUserType(account.getType());
		
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		} catch (FailedLoginException fe) {
			throw new FailedLoginException("Login failed. Please, check your credentials or Sign Up.");
		}

    }
}
