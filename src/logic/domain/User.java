package logic.domain;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;

import logic.persistence.dao.UserDAO;

public class User implements Serializable{
	
	private static final long serialVersionUID = -4203020823149238767L;
	
	private String firstName;
	private String lastName;
	private String email;
	private String pwd;
	private String city;
	private LocalDate birth;
	
	public User() {
		/**/
	}
	
	public User (String e, String p) {
		this.email = e;
		this.pwd = p;
	}
	
	public User (String e, String p, String f, String l) {
		this.email = e;
		this.pwd = p;
		this.firstName = f;
		this.lastName = l;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	
	public User getPersonalInfoFromDB(Long id) throws SQLException {
		return UserDAO.selectPersonalInfo(id);
	}

	public void savePersonalInfoOnDB(Long id) throws SQLException {
		UserDAO.updatePersonalInfo(this, id);
	}

}
