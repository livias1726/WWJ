package logic.domain;

import java.io.Serializable;
import java.util.List;

public class Company implements Serializable{
	
	private static final long serialVersionUID = -880952940086847441L;
	private String name;
	private String description;
	private Address headquarter;
	private List<Address> branches;
	
	public Company() {
		/**/
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
}
