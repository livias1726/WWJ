package logic.domain;

import java.util.ArrayList;
import java.util.List;

public class BusinessInCountry extends Business {
	private float averageManagementCost;
	private float averageEarnings;
	private float description;
	private Country country;
	
	public float getAverageManagementCost() {
		return averageManagementCost;
	}

	public void setAverageManagementCost(float averageManagementCost) {
		this.averageManagementCost = averageManagementCost;
	}

	public float getAverageEarnings() {
		return averageEarnings;
	}

	public void setAverageEarnings(float averageEarnings) {
		this.averageEarnings = averageEarnings;
	}

	public float getDescription() {
		return description;
	}

	public void setDescription(float description) {
		this.description = description;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	
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
