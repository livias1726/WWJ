package logic.bean;

import java.util.List;

import logic.application.control.EntrepreneurAccountControl;
import logic.application.control.ViewBusinessControl;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;

public class BusinessInCountryBean extends BusinessBean{

	private Float averageManagementCost;
	private Float averageEarnings;
	private String description;
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

	public CountryBean getCountry() {
		return country;
	}

	public void setCountry(CountryBean country) {
		this.country = country;
	}

	public List<BusinessInCountryBean> getBusinesses(CountryBean country, BusinessInCountryBean bus) throws NoResultFoundException, DatabaseFailureException{
		return ViewBusinessControl.getInstance().retrieveBusinesses(country, bus);
	}
	
	public List<BusinessInCountryBean> getBusinesses(CountryBean country) throws NoResultFoundException, DatabaseFailureException{
		return ViewBusinessControl.getInstance().retrieveBusinessesByCountry(country);
	}
	
	public List<BusinessInCountryBean> getBusinesses(BusinessInCountryBean bus) throws NoResultFoundException, DatabaseFailureException{
		return ViewBusinessControl.getInstance().retrieveBusinessesByName(bus);
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
