package logic.application.control;

import java.sql.SQLException;

import logic.domain.BusinessInCountry;
import logic.domain.Country;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.bean.BusinessInCountryBean;

public class StatisticsControl {

	private static StatisticsControl instance = null;

    private StatisticsControl() {
    	/*Default constructor*/
    }

    public static StatisticsControl getInstance() {
        if(instance == null) {
        	instance = new StatisticsControl();
        }

        return instance;
    }

	public void retrieveBusinessStatistics(BusinessInCountryBean business) throws DatabaseFailureException {
		BusinessInCountry bus = new BusinessInCountry();
		bus.setId(business.getId());
		Country country = new Country();
		country.setName(business.getCountry().getName());
		bus.setCountry(country);
		
		try {
			bus.getBusinessStatistics();
			business.setAverageEarnings(bus.getAverageEarnings());
			business.setAverageCost(bus.getAverageCost());
			business.setPopularity(bus.getPopularity());
			business.setCompetitors(bus.getCompetitors());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}
}
