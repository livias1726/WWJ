package logic.domain;

import java.io.File;
import java.io.Serializable;

import logic.application.Users;

public class Account implements Serializable{
	
	private static final long serialVersionUID = -9081974993803980612L;
	
	protected long id;
	protected User user;
	protected Users type;
	protected File pic;
	protected boolean premium = false;
	
	public Account() {
		/*Default constructor*/
	}
	
	public Account(User u, Users t, long id) {
		this.user = u;
		this.type = t;
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public Users getType() {
		return type;
	}

	public long getID() {
		return id;
	}
	
	public boolean isPremium(){
		return premium;
	}
	
	public void setPremium(boolean val) {
		this.premium = val;
	}
}
