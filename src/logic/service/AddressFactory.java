package logic.service;

import java.util.ArrayList;
import java.util.List;

import logic.domain.Address;
import logic.domain.Country;
import logic.presentation.bean.AddressBean;
import logic.presentation.bean.CountryBean;

public class AddressFactory implements Entity{
    
	@Override
    public Address createObject() {
    	return new Address();
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
	
	public List<Address> extractAddressList(List<AddressBean> src) {
		List<Address> dest = new ArrayList<>();
		
		for(AddressBean bean: src) {
			Address addr = createObject();
			
			Country country = (new CountryFactory()).createObject();
			country.setName(bean.getCountry().getName());
			
			addr.setCountry(country);
			addr.setState(bean.getState());
			addr.setCity(bean.getCity());
			addr.setPostalCode(bean.getPostalCode());
			addr.setStreet(bean.getStreet());
			addr.setNumber(bean.getNumber());
			addr.setId(bean.getId());
			
			dest.add(addr);
		}
		
		return dest;
	}
	
	public List<AddressBean> extractAddressBeanList(List<Address> src) {
		List<AddressBean> dest = new ArrayList<>();
		
		for(Address i: src) {
			AddressBean bean = new AddressBean();			
			CountryBean country = new CountryBean();
			country.setName(i.getCountry().getName());		
			bean.setCountry(country);
			
			bean.setState(i.getState());
			bean.setCity(i.getCity());
			bean.setPostalCode(i.getPostalCode());
			bean.setStreet(i.getStreet());
			bean.setNumber(i.getNumber());
			bean.setId(i.getId());
			
			dest.add(bean);
		}
		
		return dest;
	}
}
