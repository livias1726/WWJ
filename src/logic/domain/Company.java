package logic.domain;

import java.io.Serializable;
import java.util.List;

import logic.bean.AddressBean;

public class Company implements Serializable{
	
	private static final long serialVersionUID = -880952940086847441L;
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
