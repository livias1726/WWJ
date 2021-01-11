package logic.presentation.bean;

import java.util.List;

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
}
