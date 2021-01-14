package logic.application.control;

import java.sql.SQLException;

import logic.application.control.adapter.EuroAdapter;
import logic.application.control.adapter.PoundAdapter;
import logic.application.control.adapter.USDAdapter;
import logic.domain.BusinessInCountry;
import logic.domain.Country;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.InvalidFieldException;
import logic.presentation.bean.BusinessInCountryBean;

public class FeasibilityControl {
	
	private static FeasibilityControl instance = null;

    private FeasibilityControl() {
    	/*Singleton*/
    }

    public static FeasibilityControl getInstance() {
        if(instance == null) {
        	instance = new FeasibilityControl();
        }

        return instance;
    }

	public Float retrieveBusinessFeasibility(BusinessInCountryBean business, String budget) throws DatabaseFailureException{

		BusinessInCountry bus = new BusinessInCountry();
		bus.setId(business.getId());
		Country country = new Country();
		country.setName(business.getCountry().getName());
		bus.setCountry(country);

		try {
			bus.getBusinessFeasibility();

			business.getCountry().setLivingExpense(bus.getCountry().getLivingExpense());
			business.getCountry().setExampleCity(bus.getCountry().getExampleCity());
			business.setStartExpense(bus.getStartExpense());
			business.setTaxes(bus.getTaxes());
			
			return calculateResult(Float.valueOf(budget), business);

		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}
	
	public Float convertValue(Float val, String newCurr, String oldCurr) throws InvalidFieldException {
		switch(newCurr) {
		case "€":
			EuroAdapter euroAdapter = new EuroAdapter(oldCurr);
			return euroAdapter.value(val);
		case "£":
			PoundAdapter poundAdapter = new PoundAdapter(oldCurr);
			return poundAdapter.value(val);
		case "$":
			USDAdapter usdAdapter = new USDAdapter(oldCurr);
			return usdAdapter.value(val);
		default:
			throw new InvalidFieldException("Currency not recognized.");
		}
	}
	
	public Float calculateResult(Float budget, BusinessInCountryBean business) {
		
		Float result = budget - business.getStartExpense() - business.getCountry().getLivingExpense();
		Float earn = business.getAverageEarnings();
		
		for(Float i: business.getTaxes()) {
			business.setAverageEarnings(earn*(1-(i/100)));
			earn = business.getAverageEarnings();
		}
		
		result = result + business.getAverageEarnings() - business.getAverageCost();
		
		return result;
	}
}
