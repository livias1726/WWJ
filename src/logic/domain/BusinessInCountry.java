package logic.domain;

import java.util.ArrayList;
import java.util.List;

public class BusinessInCountry extends Business {
	private float averageManagementCost;
	private float averageEarnings;
	private float description;
	private Country country;
	
	public List<BusinessInCountry> getBusinessesByName(){
		/**DAO INTERACTION*/
		
		/*DUMMY BEHAVIOR*/
		List<BusinessInCountry> list = new ArrayList<>();

		list.add(new BusinessInCountry());

		return list;
	}
	
	public List<BusinessInCountry> getBusinessesByPlace(){
		/**DAO INTERACTION*/
		
		/*DUMMY BEHAVIOR*/
		List<BusinessInCountry> list = new ArrayList<>();

		list.add(new BusinessInCountry());

		return list;
	}
	
	public List<BusinessInCountry> getBusinessesInCountry(){
		/**DAO INTERACTION*/
		
		/*DUMMY BEHAVIOR*/
		List<BusinessInCountry> list = new ArrayList<>();

		list.add(new BusinessInCountry());

		return list;
	}
}
