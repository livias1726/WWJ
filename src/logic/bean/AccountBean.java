package logic.bean;

import java.io.File;
import java.util.List;

import javax.security.auth.login.FailedLoginException;

import logic.application.control.AccountControl;
import logic.application.control.LoginControl;
import logic.application.control.SignUpControl;
import logic.exceptions.DatabaseFailureException;

public class AccountBean {
	
	protected UserBean user;
	protected String type;
	protected File pic;

	protected boolean premium = false;
	protected long id;

	public AccountBean() {
		/*Default constructor*/
	}
	
	public AccountBean(UserBean u, String t, long id) {
		this.user = u;
		this.type = t;
		this.id = id;
	}
	
	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}
	
	public AccountBean getAccount() throws DatabaseFailureException {
		return AccountControl.getInstance().retrieveAccount();
	}
	
	public void updatePic(File img) {
		AccountControl.getInstance().updateAccountPic(img);
	}

	public List<String> getNotification() throws DatabaseFailureException {
		return AccountControl.getInstance().retrieveNotifications();
	}

	public void login() throws FailedLoginException, DatabaseFailureException {
		LoginControl.getInstance().tryLogin(user.getEmail(), user.getPassword());
	}
	
	public void signUp() throws FailedLoginException, DatabaseFailureException {
    	SignUpControl.getInstance().trySignUp(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName());
    }
   
}
