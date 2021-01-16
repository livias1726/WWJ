package logic.service;

import logic.domain.Country;

public class CountryFactory {

	private static CountryFactory instance = null;

    private CountryFactory() {
    	/*Default constructor*/
    }

    public static CountryFactory getInstance() {
        if(instance == null) {
        	instance = new CountryFactory();
        }

        return instance;
    }
    
    public Country createCountry() {
    	return new Country();
    }
}
