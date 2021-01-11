package logic.presentation.bean;

import java.io.File;

import logic.application.SessionFacade;
import logic.application.Users;

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
		if(SessionFacade.getSession().getCurrUserType() == null) {
			SessionFacade.getSession().setCurrUserType(Users.stringToUsers(type));
		}
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
}
