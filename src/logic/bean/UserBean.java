package logic.bean;

import java.time.LocalDate;
import java.util.List;

import javax.security.auth.login.FailedLoginException;

import logic.application.LoginControl;
import logic.application.SignUpControl;
import logic.exceptions.InvalidFieldException;

public class UserBean {

	private String email;
    private String password;
    private String firstName;
	private String lastName;
	private String city;
	private LocalDate birth;
	private List<String> titles;
	
	public UserBean() {
		/*Default constructor*/
	}
	
    public UserBean(String e, String p) {
    	this.password = p;
    	this.email = e;
    }
    
    public UserBean(String e, String ce, String p, String cp, String f, String l) throws InvalidFieldException {
    	if(!ce.equals(e)) {
    		throw new InvalidFieldException("The email addresses do not match");
    	}
    	
    	if(!cp.equals(p)) {
    		throw new InvalidFieldException("The passwords do not match");
    	}
    	
    	this.email = e;
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
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public List<String> getTitles() {
		return titles;
	}

	public void setTitles(List<String> titles) {
		this.titles = titles;
	}
	
	public boolean checkSyntax(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	    return email.matches(regex);
	}
	
	public boolean checkFieldValidity(String str) {
		return (str == null || str.equals(""));
	}

    public boolean verifyLogin() throws FailedLoginException{
        if (checkFieldValidity(email) || checkFieldValidity(password)) {
            return false;
        }

        if(LoginControl.getInstance().login(email, password)) {
        	return true;
        }else{
        	throw new FailedLoginException("Login has failed. Retry.");
        }
    }
    
    public void verifyUser() throws InvalidFieldException {
		if(checkFieldValidity(email) || checkFieldValidity(password) || checkFieldValidity(firstName) || checkFieldValidity(lastName)) {		
			throw new InvalidFieldException("Please, fill out every field.");		
		}
		
		if(!checkSyntax(email)) {
			throw new InvalidFieldException("The email address is not valid");	
		}
	}
    
    public void trySignUp() {
    	SignUpControl.getInstance().signUp(email, password, firstName, lastName);
    }
    
    public void update() throws InvalidFieldException {
    	verifyUser();
    	while(titles.contains("")) {
    		titles.remove("");
    	}
    	AccountBean account = new AccountBean();
    	account.updateInfo();
    }
}
