package logic.bean;

import java.io.File;

import logic.application.AccountControl;
import logic.application.Users;
import logic.domain.User;

public class AccountBean {
	
	protected User user;
	protected Users type;
	protected File pic;

	protected boolean premium = false;
	protected long id;

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
	
	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}
	
	public AccountBean retrieveInfo() {
		return AccountControl.getInstance().retrieveAccount();
	}
	
	public void updateInfo(){
		AccountControl.getInstance().updateAccount();
	}
	
	public void updatePic(File img) {
		AccountControl.getInstance().updateAccountPic(img);
	}
}
