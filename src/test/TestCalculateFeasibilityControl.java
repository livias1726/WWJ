package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.application.control.CalculateFeasibilityControl;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.InvalidFieldException;
import logic.presentation.bean.BusinessInCountryBean;
import logic.presentation.bean.CountryBean;

/**
 * @author alison petrilli
 */

public class TestCalculateFeasibilityControl {

	@Test
	public void testConvertValue() throws InvalidFieldException {
		/*10 Canadian dollars should correspond to 6.47 Euros, given a possible fluctuation of 0.1(Source: https://www.morningstar.com/)*/
		float val = 10;
		Float res = CalculateFeasibilityControl.getInstance().convertValue(val, "EUR", "C$");
		assertEquals(6.47, res, 0.1);
	}
	
	@Test
	public void testConvertValueException() {
		/*The system does not support another currency other then EUR, USD and GBP*/
		float val = 1;
		assertThrows(InvalidFieldException.class, () -> CalculateFeasibilityControl.getInstance().convertValue(val, "C$", "C$"));
	}
	
	@Test
	public void testRetrieveBusinessFeasibilityResult() throws DatabaseFailureException {
		/*Check a correct result calculation using a way too low budget and earnings*/
		BusinessInCountryBean param = new BusinessInCountryBean();
		param.setId(2);
		param.setAverageCost((float) 20000);
		param.setAverageEarnings((float) 300);
		
		CountryBean country = new CountryBean();
		country.setName("Canada");
		param.setCountry(country);
		
		Float res = CalculateFeasibilityControl.getInstance().retrieveBusinessFeasibility(param, "1");
		assertTrue(res < 0);
	}

}
