package logic.application;

import logic.bean.LoginBean;

/**Application of the Singleton pattern */

public class LoginControl {
	
	private static LoginControl INSTANCE = null;

    private LoginControl() {}

    public static LoginControl getInstance() {
        if(INSTANCE==null) {
            INSTANCE = new LoginControl();
        }

        return INSTANCE;
    }

    /*DUMMY*/
    public LoginBean login(String username, String password) {
    	LoginBean log;
        //USAGE OF A DAO TO ACCESS THE DB   
    	int choose = (int)Math.random()%4;
    	switch(choose) {
    		case 0:
    			log = new LoginBean("recruiter", password);
    			break;
    		case 1:
    			log = new LoginBean("unemployed", password);
    			break;
    		case 2:
    			log = new LoginBean("entrepreneur", password);
    			break;
    		default:
    			log = null;
    			break;
    	}
    	
        return log;
    }
    
}
