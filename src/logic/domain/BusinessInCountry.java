package logic.domain;

import java.sql.SQLException;
import java.util.List;

import logic.exceptions.NoResultFoundException;
import logic.persistence.dao.BusinessDAO;

public class BusinessInCountry extends Business {
	
	private Float averageCost;
	private Float averageEarnings;
	private String description;
	private List<Float> popularity;
	private List<Integer> competitors;
	private List<Float> taxes;
	private Float startExpense;
	private Country country;
	
	public BusinessInCountry() {
		/**/
	}
	
	public BusinessInCountry(int id) {
		this.busId = id;
	}

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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	public Float getStartExpense() {
		return startExpense;
	}

	public void setStartExpense(Float startExpense) {
		this.startExpense = startExpense;
	}
	
	public List<Float> getTaxes() {
		return taxes;
	}

	public void setTaxes(List<Float> taxes) {
		this.taxes = taxes;
	}
	
	public List<BusinessInCountry> getBusinessesByCategory(String business) throws SQLException, NoResultFoundException{
		return BusinessDAO.selectBusinessByCategory(business);
	}
	
	public List<BusinessInCountry> getBusinessesByPlace(String country) throws SQLException, NoResultFoundException{
		return BusinessDAO.selectBusinessByCountry(country);
	}
	
	public List<BusinessInCountry> getBusinessesInCountry(String country, String bus) throws SQLException, NoResultFoundException{
		return BusinessDAO.selectBusinessInCountry(country, bus);
	}

	public List<BusinessInCountry> getFavourites(Long id) throws SQLException {
		return BusinessDAO.selectFavourites(id);
	}

	public void addFavourite(Long id) throws SQLException {
		BusinessDAO.insertIntoFavourite(this.busId, id);
	}

	public void deleteFavourite(Long id) throws SQLException {
		BusinessDAO.deleteFromFavourite(this.busId, id);
	}

	public void getBusinessStatistics() throws SQLException {
		BusinessDAO.selectBusinessStatistics(this);
	}

	public void getBusinessFeasibility() throws SQLException {
		BusinessDAO.selectBusinessFeasibility(this);
	}
}
