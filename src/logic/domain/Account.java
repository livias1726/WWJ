package logic.domain;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.security.auth.login.FailedLoginException;

import logic.application.Users;
import logic.persistence.dao.AccountDAO;

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
	
	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public Account(User u, Users t, long id) {
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

	public long getID() {
		return id;
	}
	
	public boolean isPremium(){
		return premium;
	}
	
	public void setPremium(boolean val) {
		this.premium = val;
	}
	
	public Account tryLoginToDB() throws FailedLoginException, SQLException {
		return AccountDAO.executeLogin(user.getEmail(), user.getPwd());
	}

	public List<String> getNotificationsFromDB(long id) throws SQLException {
		return AccountDAO.selectNotifications(id);
	}

	public Account getAccountFromDB(long id) throws SQLException, IOException {
		return AccountDAO.selectAccount(id);
	}

	public boolean createAccountOnDB() throws SQLException{
		String typeStr = Users.usersToString(this.getType());
		return AccountDAO.createAccount(this.user.getEmail(), this.user.getPwd(), this.user.getFirstName(), this.user.getLastName(), typeStr);
	}

	public void savePic(File img, Long id) throws IOException, SQLException {
		AccountDAO.updatePic(img, id);
	}
}
