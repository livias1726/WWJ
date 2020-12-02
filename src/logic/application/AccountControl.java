package logic.application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import logic.bean.AccountBean;
import logic.bean.UserBean;
import logic.domain.Account;

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
    public AccountBean retrieveAccount(long id) {
    	/*Access the DB and deserialize the 
    	 * Account info related to the id
    	 */
    	
    	Account newAccount = new Account();
    	long dbId = 0;
    	
    	/*TEMPORARY BEHAVIOR: save account on file*/  	
    	while(id != dbId) {
    		try (FileInputStream fileIn = new FileInputStream("src/logic/persistence/database/account.txt"); 
    	    		ObjectInputStream objectIn = new ObjectInputStream(fileIn)) { 		 
    	            newAccount = (Account)objectIn.readObject();
    	            
    	            dbId = newAccount.getID();
    	           	            
	        } catch (FileNotFoundException e) {
	            System.err.print("File not found.");
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
    	}
    	
    	AccountBean result = new AccountBean(newAccount.getUser(), newAccount.getType(), newAccount.getID());
        SessionFacade.getSession().setID(result.getId());
        return result;
    }
    
    public void updateAccount(UserBean bean) {
    	
    	/*TEMPORARY BEHAVIOR: save account on file*/  
    	Account account = new Account();
    	try (FileInputStream fileIn = new FileInputStream("src/logic/persistence/database/account.txt"); 
	    		ObjectInputStream objectIn = new ObjectInputStream(fileIn)) { 	
			
	            account = (Account)objectIn.readObject();
	            
        } catch (FileNotFoundException e) {
            System.err.print("File not found.");
        } catch (Exception e) {
        	e.printStackTrace();
        }
    	
    	account.getUser().setEmail(bean.getEmail());
    	account.getUser().setPwd(bean.getPassword());
    	account.getUser().setFirstName(bean.getFirstName());
    	account.getUser().setLastName(bean.getLastName());
    	account.getUser().setCity(bean.getCity());
    	account.getUser().setBirth(bean.getBirth());
    	account.getUser().setTitles(bean.getTitles());
    	
    	/*TEMPORARY BEHAVIOR: save account on file*/  	
    	try (FileOutputStream fileOut = new FileOutputStream("src/logic/persistence/database/account.txt"); 
    		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) { 		 
            objectOut.writeObject(account);
        } catch (FileNotFoundException e) {
            System.err.print("File not found.");
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}
