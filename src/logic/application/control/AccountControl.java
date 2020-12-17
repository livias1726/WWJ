package logic.application.control;

import java.io.File;

import logic.application.SessionFacade;
import logic.application.Users;
import logic.bean.AccountBean;
import logic.domain.User;

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

    /*DUMMY*/
    public AccountBean retrieveAccount(){
    	
    	/*Access the DB and deserialize the Account info related to the Session id*/

    	User user = new User("star@gmail.com", "ciao", "Pinco", "Panco");
    
        return new AccountBean(user, Users.RECRUITER, SessionFacade.getSession().getID());
        //return new AccountBean(user, Users.SEEKER, SessionFacade.getSession().getID());
        //return new AccountBean(user, Users.ENTREPRENEUR, SessionFacade.getSession().getID());
    }
    
    public void updateAccount() {
    	/*Update personal info for the account*/
    }
    
    public void updateAccountPic(File img) {
    	/*Update image field for the account*/
    }
}
