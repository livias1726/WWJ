package logic.domain;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import logic.persistence.dao.CompanyDAO;

public class Company implements Serializable{
	
	private static final long serialVersionUID = -880952940086847441L;
	private String name;
	private String description;
	private Address headquarter;
	private List<Address> branches;
	
	public Company() {
		/**/
	}
	
	public Company(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Address getHeadquarter() {
		return headquarter;
	}

	public void setHeadquarter(Address headquarter) {
		this.headquarter = headquarter;
	}

	public List<Address> getBranches() {
		return branches;
	}

	public void setBranches(List<Address> branches) {
		this.branches = branches;
	}
	
	public Company getCompanyInfoFromDB(Long id) throws SQLException {
		return CompanyDAO.selectCompanyInfo(id);
	}

	public void saveCompanyInfoOnDB(Long id) throws SQLException {
		CompanyDAO.updateCompanyInfo(this, id);
	}
}
