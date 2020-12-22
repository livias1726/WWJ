package logic.domain;

import java.util.ArrayList;
import java.util.List;

public class Business {

	private String category;
	private String name;

	public List<String> getAvailableBusinesses(){
		/**DAO INTERACTION*/
		
		/*DUMMY BEHAVIOR*/
		List<String> list = new ArrayList<>();
		list.add("Tenant");
		return list;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
