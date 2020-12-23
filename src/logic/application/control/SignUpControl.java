package logic.application.control;

import java.sql.SQLException;

import javax.security.auth.login.FailedLoginException;

import logic.application.SessionFacade;
import logic.domain.Account;
import logic.domain.User;
import logic.exceptions.DatabaseFailureException;

public class SignUpControl {
	
	private static SignUpControl instance = null;

    private SignUpControl() {
    	/*Default constructor*/
    }

    public static SignUpControl getInstance() {
        if(instance == null) {
        	instance = new SignUpControl();
        }

        return instance;
    }
    
    public void trySignUp(String email, String password, String firstName, String lastName) throws FailedLoginException, DatabaseFailureException {
    
    	User user = new User(email, password, firstName, lastName);
    	Account account = new Account();
    	account.setUser(user);
    	account.setType(SessionFacade.getSession().getCurrUserType());
    	
    	try {
			if(account.createAccountOnDB()) {
				LoginControl.getInstance().tryLogin(email, password);
			}
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
    }
}
