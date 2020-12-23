package logic.bean;

import java.time.LocalDate;

import logic.application.control.AccountControl;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.InvalidFieldException;

public class UserBean {

	private String email;
    private String password;
    private String firstName;
	private String lastName;
	private String city;
	private LocalDate birth;
	
	public UserBean() {
		/*Default constructor*/
	}
	
    public UserBean(String e, String p) {
    	this.password = p;
    	this.email = e;
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
	
	public boolean checkFieldValidity(String str) {
		return (str == null || str.equals(""));
	}

    public void verifyFields(String...params) throws InvalidFieldException{
    	for (int i = 0; i < params.length; i++) {
    		if (checkFieldValidity(params[i])) {
                throw new InvalidFieldException("Please, fill out every field");
            }
		}     
    }
    
    public void verifySyntax() throws InvalidFieldException {
    	String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	    
		if(!email.matches(regex)) {
			throw new InvalidFieldException("The email address is not valid");	
		}
	}
    
    public void verifyEqualFields(String email, String pwd) throws InvalidFieldException {
    	if(!email.equals(this.email)) {
    		throw new InvalidFieldException("The email addresses do not match");
    	}
    	
    	if(!pwd.equals(this.password)) {
    		throw new InvalidFieldException("The passwords do not match");
    	}
    }
   
	public UserBean getPersonalInfo() throws DatabaseFailureException {
		return AccountControl.getInstance().retrievePersonalInfo();
	}
}
