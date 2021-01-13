package logic.presentation.bean;

import java.util.List;

import logic.application.control.EntrepreneurAccountControl;
import logic.exceptions.DatabaseFailureException;

public class BusinessInCountryBean extends BusinessBean{

	private Float averageCost;
	private Float averageEarnings;
	private List<Float> popularity;
	private List<Integer> competitors;
	private String description;
	private CountryBean country;
	
	public Float getAverageCost() {
		return averageCost;
	}

	public void setAverageCost(Float averageCost) {
		this.averageCost = averageCost;
	}

	public List<Float> getPopularity() {
		return popularity;
	}

	public void setPopularity(List<Float> popularity) {
		this.popularity = popularity;
	}

	public List<Integer> getCompetitors() {
		return competitors;
	}

	public void setCompetitors(List<Integer> competitors) {
		this.competitors = competitors;
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
