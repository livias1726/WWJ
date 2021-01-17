package logic.application.control;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.application.Users;
import logic.domain.Account;
import logic.domain.User;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.DuplicatedUserException;
import logic.presentation.bean.AccountBean;
import logic.service.AbstractFactory;
import logic.service.Factory;
import logic.service.Types;

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
    	AbstractFactory factoryAcc = Factory.getInstance().getObject(Types.ACCOUNT);
		Account account = (Account)factoryAcc.createObject();
		
		AbstractFactory factoryUs = Factory.getInstance().getObject(Types.USER);
		User user = (User)factoryUs.createObject();
		user.setEmail(bean.getUser().getEmail());
		user.setPwd(bean.getUser().getPassword());
		user.setFirstName(bean.getUser().getFirstName());
		user.setLastName(bean.getUser().getLastName());
		
    	account.setUser(user);
    	account.setType(Users.stringToUsers(bean.getType()));
    	try {
    		account.createAccountOnDB();
		} catch (SQLException e) {
			if(e.getSQLState() != null && e.getSQLState().equals("45000")) {
				throw new DuplicatedUserException();
			}else {
				Logger.getLogger(SignUpControl.class.getName()).log(Level.SEVERE, null, e);
				throw new DatabaseFailureException();
			}			
		}
    }
}
