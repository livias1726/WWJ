package logic.application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

import logic.domain.Account;

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
    public boolean login(String email, String password) {
        	
    	/**
    	 * Access the DB and retrieve credentials...
    	 * 
    	 * EXAMPLE DUMMY
    	 */
       	
    	Account account = new Account();
    	
    	/*TEMPORARY BEHAVIOR: save account on file*/  	
    	do {
    		try (FileInputStream fileIn = new FileInputStream("src/logic/persistence/database/account.txt"); 
    	    		ObjectInputStream objectIn = new ObjectInputStream(fileIn)) { 		 
    	            account = (Account)objectIn.readObject();
    	           	            
	        } catch (FileNotFoundException e) {
	            System.err.print("File not found.");
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
    	} while(!email.equals(account.getUser().getEmail()) && !password.equals(account.getUser().getPwd()));
    	
    	SessionFacade.getSession().setCurrUserType(account.getType());
    	SessionFacade.getSession().setID(account.getID());
    	
    	return true;
    }
    
}
