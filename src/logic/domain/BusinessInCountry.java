package logic.domain;

import java.sql.SQLException;
import java.util.List;

import logic.exceptions.NoResultFoundException;
import logic.persistence.dao.BusinessDAO;

public class BusinessInCountry extends Business {
	private Float averageManagementCost;
	private Float averageEarnings;
	private String description;
	private Country country;
	
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	public List<BusinessInCountry> getBusinessesByName(String business) throws SQLException, NoResultFoundException{
		return BusinessDAO.selectBusinessByName(business);
	}
	
	public List<BusinessInCountry> getBusinessesByPlace(String country) throws SQLException, NoResultFoundException{
		return BusinessDAO.selectBusinessByCountry(country);
	}
	
	public List<BusinessInCountry> getBusinessesInCountry(String country, String bus) throws SQLException, NoResultFoundException{
		return BusinessDAO.selectBusinessInCountry(country, bus);
	}

	public List<BusinessInCountry> getFavourites(Long id) throws SQLException {
		return BusinessDAO.selectFavourites(id.toString());
	}

	public void addFavourite(Long id) throws SQLException {
		BusinessDAO.insertIntoFavourite(this.id, id);
	}

	public void deleteFavourite(Long id) throws SQLException {
		BusinessDAO.deleteFromFavourite(this.id, id);
	}
}
