package logic.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Job implements Serializable{

	private static final long serialVersionUID = 7555734428882964601L;
	private String name;
	private String category;
	
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
	
	public List<String> getAvailableJobs(){
		/**DAO INTERACTION*/
		
		/*DUMMY BEHAVIOR*/
		List<String> list = new ArrayList<>();
		list.add("Software engineer");
		return list;
	}
}
