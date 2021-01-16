package logic.application.control;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.domain.BusinessInCountry;
import logic.domain.Country;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.bean.BusinessInCountryBean;
import logic.service.BusinessFactory;
import logic.service.CountryFactory;

public class ViewStatisticsControl {

	private static ViewStatisticsControl instance = null;

    private ViewStatisticsControl() {
    	/*Default constructor*/
    }

    public static ViewStatisticsControl getInstance() {
        if(instance == null) {
        	instance = new ViewStatisticsControl();
        }

        return instance;
    }

	public void retrieveBusinessStatistics(BusinessInCountryBean business) throws DatabaseFailureException {
		BusinessInCountry bus = BusinessFactory.getInstance().createBusiness(business.getId());
		Country country = CountryFactory.getInstance().createCountry();
		country.setName(business.getCountry().getName());
		bus.setCountry(country);
		
		try {
			bus.getBusinessStatistics();
			business.setAverageEarnings(bus.getAverageEarnings());
			business.setAverageCost(bus.getAverageCost());
			business.setPopularity(bus.getPopularity());
			business.setCompetitors(bus.getCompetitors());
		} catch (SQLException e) {
			Logger.getLogger(ViewStatisticsControl.class.getName()).log(Level.SEVERE, "SQLException thrown", e);
			throw new DatabaseFailureException();
		}
	}
}
