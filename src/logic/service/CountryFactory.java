package logic.service;

import logic.domain.Country;

public class CountryFactory implements Entity{

	@Override
    public Country createObject() {
    	return new Country();
    }
}
