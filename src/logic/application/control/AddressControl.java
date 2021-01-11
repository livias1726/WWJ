package logic.application.control;

import logic.domain.Address;
import logic.domain.Country;
import logic.presentation.bean.AddressBean;
import logic.presentation.bean.CountryBean;

public class AddressControl {
	
	private static AddressControl instance = null;

    private AddressControl() {
    	/*Default constructor*/
    }

    public static AddressControl getInstance() {
        if(instance == null) {
        	instance = new AddressControl();
        }

        return instance;
    }
    
    public Address extractAddress(AddressBean bean) {
		Address addr = new Address();
		
		Country country = new Country();
		country.setName(bean.getCountry().getName());
		
		addr.setCountry(country);
		addr.setState(bean.getState());
		addr.setCity(bean.getCity());
		addr.setPostalCode(bean.getPostalCode());
		addr.setStreet(bean.getStreet());
		addr.setNumber(bean.getNumber());
		addr.setId(bean.getId());
		
		return addr;
	}
		
	
	public AddressBean extractAddressBean(Address ent) {
		AddressBean bean = new AddressBean();
		
		CountryBean country = new CountryBean();
		country.setName(ent.getCountry().getName());		
		bean.setCountry(country);
		
		bean.setState(ent.getState());
		bean.setCity(ent.getCity());
		bean.setPostalCode(ent.getPostalCode());
		bean.setStreet(ent.getStreet());
		bean.setNumber(ent.getNumber());
		bean.setId(ent.getId());
		return bean;
	}
}
