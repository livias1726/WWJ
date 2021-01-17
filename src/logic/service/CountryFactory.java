package logic.service;

import logic.domain.Country;

public class CountryFactory implements AbstractFactory{

	@Override
    public Country createObject() {
    	return new Country();
    }
}
