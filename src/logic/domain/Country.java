package logic.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Country implements Serializable{

	private static final long serialVersionUID = 8047822125312096938L;
	
	public List<String> getAvailableCountries(){
		/**DAO INTERACTION*/
		
		/*DUMMY BEHAVIOR*/
		List<String> list = new ArrayList<>();
		list.add("Canada");
		return list;
	}
}
