package logic.application.control;

import java.sql.SQLException;

import logic.application.Users;
import logic.domain.Account;
import logic.domain.User;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.DuplicatedUserException;
import logic.presentation.bean.AccountBean;

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
    
    public void trySignUp(AccountBean bean) throws DatabaseFailureException, DuplicatedUserException {   
    	User user = new User(bean.getUser().getEmail(), bean.getUser().getPassword(), bean.getUser().getFirstName(), bean.getUser().getLastName());
    	Account account = new Account();
    	account.setUser(user);
    	account.setType(Users.stringToUsers(bean.getType()));
    	try {
    		account.createAccountOnDB();
		} catch (SQLException e) {
			if(e.getSQLState() != null && e.getSQLState().equals("45000")) {
				throw new DuplicatedUserException();
			}else {
				throw new DatabaseFailureException();
			}			
		}
    }
}
