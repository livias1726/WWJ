package logic.service;

import logic.domain.CV;

public class CVFactory implements Entity {

	@Override
    public CV createObject() {
    	return new CV();
    }

}
