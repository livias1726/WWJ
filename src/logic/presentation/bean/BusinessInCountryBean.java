package logic.presentation.bean;

import java.util.List;

import logic.application.control.EntrepreneurAccountControl;
import logic.exceptions.DatabaseFailureException;

public class BusinessInCountryBean extends BusinessBean{

	private Float averageManagementCost;
	private Float averageEarnings;
	private String description;
	private int rank;
	private CountryBean country;
	
	public Float getAverageManagementCost() {
		return averageManagementCost;
	}

	public void setAverageManagementCost(Float averageManagementCost) {
		this.averageManagementCost = averageManagementCost;
	}

	public Float getAverageEarnings() {
		return averageEarnings;
	}

	public void setAverageEarnings(Float averageEarnings) {
		this.averageEarnings = averageEarnings;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public CountryBean getCountry() {
		return country;
	}

	public void setCountry(CountryBean country) {
		this.country = country;
	}

	public List<BusinessInCountryBean> getFavouriteBusinesses() throws DatabaseFailureException {
		return EntrepreneurAccountControl.getInstance().retrieveFavourites();
	}

	public void addToFavourites() throws DatabaseFailureException {
		EntrepreneurAccountControl.getInstance().addNewFavourite(id);
	}

	public void removeFromFavourites() throws DatabaseFailureException {
		EntrepreneurAccountControl.getInstance().removeFavourites(id);
	}
}
