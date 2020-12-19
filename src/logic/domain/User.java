package logic.domain;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.security.auth.login.FailedLoginException;

import logic.persistence.dao.UserDAO;

public class User implements Serializable{
	
	private static final long serialVersionUID = -4203020823149238767L;
	
	private String firstName;
	private String lastName;
	private String email;
	private String pwd;
	private String city;
	private LocalDate birth;
	private List<String> titles;
	
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

	public List<String> getTitles() {
		return titles;
	}

	public void setTitles(List<String> titles) {
		this.titles = titles;
	}
	
	public Account login() throws FailedLoginException, SQLException {
		return UserDAO.loginToSystem(this.email, this.pwd);
	}
}
