package logic.application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import logic.domain.Account;
import logic.domain.User;

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
    
    /*DUMMY*/
    public void signUp(String email, String password, String firstName, String lastName){
    	
    	User user = new User(email, password, firstName, lastName);
    	/*Create a new entry in the database: retrieve the next id number to associate to the account*/
    	Account newAccount = new Account(user, SessionFacade.getSession().getCurrUserType(), 234);
    	
    	/*TEMPORARY BEHAVIOR: save account on file*/  	
    	try (FileOutputStream fileOut = new FileOutputStream("src/logic/persistence/database/account.txt"); 
    		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) { 		 
            objectOut.writeObject(newAccount);
        } catch (FileNotFoundException e) {
            System.err.print("File not found.");
        } catch (Exception e) {
        	e.printStackTrace();
        }
    	/*Accociate the account id with the session*/
    	SessionFacade.getSession().getID();
    	/*Save Account*/
    }
}
