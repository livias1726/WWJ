package logic.service;

public class Factory {
	
	private static Factory instance = null;

    private Factory() {
    	//Default constructor
    }

    public static Factory getInstance() {
        if(instance == null) {
        	instance = new Factory();
        }

        return instance;
    }

	public AbstractFactory getObject(Types type) {
		switch(type) {
		case OFFER:
			return new OfferFactory();
		case BUSINESSINCOUNTRY:
			return new BusinessInCountryFactory();
		case COUNTRY:
			return new CountryFactory();
		case BUSINESS:
			return new BusinessFactory();
		case JOB:
			return new JobFactory();
		case APPLICATION:
			return new ApplicationFactory();
		case CANDIDATE:
			return new CandidateFactory();
		case ACCOUNT:
			return new AccountFactory();
		case USER:
			return new UserFactory();
		case COMPANY:
			return new CompanyFactory();
		case ADDRESS:
			return new AddressFactory();
		case CV:
			return new CVFactory();
		default:
			return null;
		}
	}
}
