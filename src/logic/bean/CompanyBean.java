package logic.bean;

import java.util.List;

import logic.application.control.RecruiterAccountControl;
import logic.exceptions.DatabaseFailureException;

public class CompanyBean {

	private String name;
	private String description;
	private AddressBean headquarter;
	private List<AddressBean> branches;
	
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
	
	public AddressBean getHeadquarter() {
		return headquarter;
	}

	public void setHeadquarter(AddressBean headquarter) {
		this.headquarter = headquarter;
	}

	public List<AddressBean> getBranches() {
		return branches;
	}

	public void setBranches(List<AddressBean> branches) {
		this.branches = branches;
	}

	public CompanyBean getCompanyInfo() throws DatabaseFailureException {
		return RecruiterAccountControl.getInstance().retrieveCompanyInfo();
	}

	public void saveCompanyInfo() throws DatabaseFailureException {
		RecruiterAccountControl.getInstance().changeCompanyInfo(this);
	}
}
