package logic.bean;

import javax.security.auth.login.FailedLoginException;

import logic.application.LoginControl;

public class LoginBean {
	
	private String username;
    private String password;
    
    public LoginBean(String u, String p) {
    	this.password = p;
    	this.username = u;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean verify() throws FailedLoginException {
        if (username == null || username.equals("") || password == null || password.equals("")) {
            return false;
        }

        LoginBean user = LoginControl.getInstance().login(username, password);
        
        if(user != null) {
        	return true;
        }else{
        	throw new FailedLoginException();
        }
    }
}
