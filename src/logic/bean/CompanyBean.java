package logic.bean;

import java.util.List;

import logic.application.control.PublishOfferControl;
import logic.application.control.RecruiterAccountControl;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.IncompleteAccountException;

public class CompanyBean {

	private String name;
	private String description;
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
	
	public List<AddressBean> getBranches() {
		return branches;
	}

	public void setBranches(List<AddressBean> branches) {
		this.branches = branches;
	}

	public CompanyBean getCompanyInfo() throws DatabaseFailureException {
		return RecruiterAccountControl.getInstance().retrieveCompanyInfo();
	}
	
	public List<AddressBean> getCompanyBranches() throws DatabaseFailureException, IncompleteAccountException {
		return PublishOfferControl.getInstance().retrieveCompanyInfo();
	}

	public void saveCompanyInfo() throws DatabaseFailureException {
		RecruiterAccountControl.getInstance().changeCompanyInfo(this);
	}
}
