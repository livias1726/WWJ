package logic.bean;

import logic.application.AccountControl;
import logic.application.Users;
import logic.domain.User;

public class AccountBean {
	
	private User user;
	private Users type;
	private boolean premium = false;
	private long id;

	public AccountBean() {
		/*Default constructor*/
	}
	
	public AccountBean(User u, Users t, long id) {
		this.user = u;
		this.type = t;
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Users getType() {
		return type;
	}

	public void setType(Users type) {
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
	
	public AccountBean retrieveInfo(long id) {
		return AccountControl.getInstance().retrieveAccount(id);
	}
	
	public void updateInfo(UserBean changes) {
		AccountControl.getInstance().updateAccount(changes);
	}
}
