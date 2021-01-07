package logic.application.control;

import java.sql.SQLException;

import javax.security.auth.login.FailedLoginException;

import logic.application.Users;
import logic.bean.AccountBean;
import logic.domain.Account;
import logic.domain.User;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.DuplicateUserException;

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
    
    public void trySignUp(AccountBean bean) throws DatabaseFailureException, DuplicateUserException {   
    	User user = new User(bean.getUser().getEmail(), bean.getUser().getPassword(), bean.getUser().getFirstName(), bean.getUser().getLastName());
    	Account account = new Account();
    	account.setUser(user);
    	account.setType(Users.stringToUsers(bean.getType()));
    	try {
			if(account.createAccountOnDB()) {
				LoginControl.getInstance().tryLogin(bean.getUser().getEmail(), bean.getUser().getPassword());
			}
		} catch (SQLException e) {
			if(e.getSQLState() != null && e.getSQLState().equals("45000")) {
				throw new DuplicateUserException();
			}else {
				throw new DatabaseFailureException();
			}			
		} catch (FailedLoginException e) {
			throw new DatabaseFailureException();
		}
    }
}
