package logic.service;

import logic.domain.CV;

public class CVFactory implements AbstractFactory {

	@Override
    public CV createObject() {
    	return new CV();
    }

}
