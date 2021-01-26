package logic.application.control;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.application.adapter.Currency;
import logic.application.adapter.EuroAdapter;
import logic.application.adapter.PoundAdapter;
import logic.application.adapter.USDAdapter;
import logic.domain.BusinessInCountry;
import logic.domain.Country;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.InvalidFieldException;
import logic.presentation.bean.BusinessInCountryBean;
import logic.service.Entity;
import logic.service.Factory;
import logic.service.Types;

public class CalculateFeasibilityControl {
	
	private static CalculateFeasibilityControl instance = null;

    private CalculateFeasibilityControl() {
    	/*Singleton*/
    }

    public static CalculateFeasibilityControl getInstance() {
        if(instance == null) {
        	instance = new CalculateFeasibilityControl();
        }

        return instance;
    }

	public Float retrieveBusinessFeasibility(BusinessInCountryBean business, String budget) throws DatabaseFailureException{
		Entity factoryBus = Factory.getInstance().getObject(Types.BUSINESSINCOUNTRY);
		BusinessInCountry bus = (BusinessInCountry)factoryBus.createObject();
		
		bus.setId(business.getId());
		
		Entity factoryCou = Factory.getInstance().getObject(Types.COUNTRY);
		Country country = (Country)factoryCou.createObject();
		
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
			Logger.getLogger(CalculateFeasibilityControl.class.getName()).log(Level.SEVERE, "SQLException thrown", e);
			throw new DatabaseFailureException();
		}
	}
	
	public Float convertValue(Float val, String newCurr, String oldCurr) throws InvalidFieldException{
		
		switch(newCurr) {
			case "€":
			case "EUR":
				Currency euroAdapter = new EuroAdapter(oldCurr);
				return euroAdapter.value(val);
			case "£":
			case "GBP":
				Currency poundAdapter = new PoundAdapter(oldCurr);
				return poundAdapter.value(val);
			case "$":
			case "USD":
				Currency usdAdapter = new USDAdapter(oldCurr);
				return usdAdapter.value(val);
			default:
				throw new InvalidFieldException("Currency not recognized.");
		}
	}
	
	public Float calculateResult(Float budget, BusinessInCountryBean business) {
		
		Float result = budget - business.getStartExpense() - business.getCountry().getLivingExpense();
		Float earn = business.getAverageEarnings();
		
		for(Float i: business.getTaxes()) {
			earn = earn*(1-(i/100));
		}
		
		result = result + earn - business.getAverageCost();
		
		return result;
	}
}
