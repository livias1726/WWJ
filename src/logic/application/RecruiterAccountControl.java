package logic.application;

import logic.bean.RecruiterAccountBean;

public class RecruiterAccountControl {
	
	private static RecruiterAccountControl instance = null;

    private RecruiterAccountControl() {
    	/*Default constructor*/
    }

    public static RecruiterAccountControl getInstance() {
        if(instance == null) {
        	instance = new RecruiterAccountControl();
        }

        return instance;
    }
    
    /*DUMMY*/
    public RecruiterAccountBean retrieveRecAccount() {
    	/*
    	 * Access the DB and deserialize the account info related to the id
    	 */
        return new RecruiterAccountBean();
    }
    
    /*DUMMY*/
    public void updateCompany(RecruiterAccountBean bean){
    	/*
    	 * Access the DB and update info
    	 */   	
    }
   
}
