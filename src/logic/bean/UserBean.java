package logic.bean;

import javax.security.auth.login.FailedLoginException;

import logic.application.LoginControl;
import logic.application.SignUpControl;
import logic.exceptions.InvalidFieldException;

public class UserBean {

	private String email;
    private String password;
    private String firstName;
	private String lastName;
	
    public UserBean(String u, String p) {
    	this.password = p;
    	this.email = u;
    }
    
    public UserBean(String u, String p, String f, String l) {
    	this.email = u;
    	this.password = p;
    	this.firstName = f;
    	this.lastName = l;
    }
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public boolean checkSyntax(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	    return email.matches(regex);
	}
	
	public boolean checkFieldValidity(String str) {
		return (str == null || str.equals(""));
	}

    public boolean verifyLogin() throws FailedLoginException {
        if (!checkFieldValidity(email) || !checkFieldValidity(password)) {
            return false;
        }

        UserBean user = LoginControl.getInstance().login(email, password);
        
        if(user != null) {
        	return true;
        }else{
        	throw new FailedLoginException();
        }
    }
    
    public void verifyNewAccount() throws InvalidFieldException {
		if(!checkFieldValidity(email) || !checkFieldValidity(password) || !checkFieldValidity(firstName) || !checkFieldValidity(lastName)) {		
			throw new InvalidFieldException("Please, fill out every field.");		
		}
		
		if(!checkSyntax(email)) {
			throw new InvalidFieldException("The email address is not valid");	
		}
		
		SignUpControl.getInstance().signUp(email, password);
	}
}
