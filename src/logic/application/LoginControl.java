package logic.application;

import java.util.Random;

import logic.bean.UserBean;

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
    public UserBean login(String username, String password) {
    	UserBean log;
    	
    	/**
    	 * Access the DB and retrieve credentials...
    	 * 
    	 * EXAMPLE DUMMY
    	 */
    	
    	int choose = (new Random()).nextInt()%4;
    	
    	switch(choose) {
    		case 0:
    			log = new UserBean("recruiter", password);
    			break;
    		case 1:
    			log = new UserBean("unemployed", password);
    			break;
    		case 2:
    			log = new UserBean("entrepreneur", password);
    			break;
    		default:
    			log = null;
    			break;
    	}
    	
        return log;
    }
    
}
