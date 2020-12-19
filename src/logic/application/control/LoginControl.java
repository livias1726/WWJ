package logic.application.control;

import java.sql.SQLException;

import javax.security.auth.login.FailedLoginException;

import logic.application.SessionFacade;
import logic.domain.Account;
import logic.domain.User;

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

    public boolean login(String email, String password){
        	
    	User user = new User(email, password);
    	Account account;
		try {
			account = user.login();
			
			if(account != null) {
	    		SessionFacade.getSession().setID(account.getID());
	    		SessionFacade.getSession().setCurrUserType(account.getType());
	    		return true;
	    	}
		} catch (FailedLoginException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
		}
    	
    	return false;
    }
    
}
