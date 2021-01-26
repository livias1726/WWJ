package logic.service;

import java.util.List;

import logic.domain.Company;
import logic.presentation.bean.AddressBean;
import logic.presentation.bean.CompanyBean;

public class CompanyFactory implements Entity {

	@Override
    public Company createObject() {
    	return new Company();
    }

	public CompanyBean extractCompanyBean(Company company) {
		CompanyBean bean = new CompanyBean();
		bean.setName(company.getName());
		bean.setDescription(company.getDescription());
	
		List<AddressBean> branches = (new AddressFactory()).extractAddressBeanList(company.getBranches());	
		bean.setBranches(branches);
		
		return bean;
	}
	
}
