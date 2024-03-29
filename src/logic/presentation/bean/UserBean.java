package logic.presentation.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logic.exceptions.BadAddressException;
import logic.exceptions.InvalidFieldException;

public class UserBean {

	private String email;
    private String password;
    private String firstName;
	private String lastName;
	private String city;
	private String birth;
	
	public UserBean() {
		/*Default constructor*/
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

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
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
    
    public void verifySyntax() throws BadAddressException {
    	Pattern pattern = Pattern.compile("^.+@.+\\..+$");
    	Matcher matcher = pattern.matcher(email);
    	if(!matcher.matches()) {
			throw new BadAddressException("The email address is not valid");	
		}
	}
    
    public void verifyEqualFields(String email, String pwd) throws InvalidFieldException {
    	if(!email.equals(this.email)) {
    		throw new InvalidFieldException("Email addresses do not match");
    	}
    	
    	if(!pwd.equals(this.password)) {
    		throw new InvalidFieldException("Passwords do not match");
    	}
    }
    
    public void verifySignUpSyntax(String email, String pwd) throws InvalidFieldException, BadAddressException {
    	verifyFields();
		verifyEqualFields(email, pwd);
		verifySyntax();
	
    	if(!email.equals(this.email)) {
    		throw new InvalidFieldException("Email addresses do not match");
    	}
    	
    	if(!pwd.equals(this.password)) {
    		throw new InvalidFieldException("Passwords do not match");
    	}
    }
}
