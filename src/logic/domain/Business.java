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
}
