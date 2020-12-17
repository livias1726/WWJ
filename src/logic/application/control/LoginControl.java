package logic.application.control;

import logic.application.SessionFacade;
import logic.application.Users;

/**Application of the Singleton pattern */
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

    /*DUMMY*/
    public boolean login(String email, String password){
        	
    	/*Access DB to retrieve the Account*/
    	
    	/*FIXED ACCOUNT FOR TESTING: RECRUITER*/
    	SessionFacade.getSession().setID((long) 1);
    	if(SessionFacade.getSession().getCurrUserType() == null) {
    		SessionFacade.getSession().setCurrUserType(Users.RECRUITER);
    		//SessionFacade.getSession().setCurrUserType(Users.SEEKER);
    		//SessionFacade.getSession().setCurrUserType(Users.ENTREPRENEUR);
    	}
    	
    	return true;
    }
    
}
