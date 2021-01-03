package logic.application.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.application.SessionFacade;
import logic.bean.BusinessInCountryBean;
import logic.bean.CountryBean;
import logic.domain.BusinessInCountry;
import logic.exceptions.DatabaseFailureException;

public class EntrepreneurAccountControl{
	
	private static EntrepreneurAccountControl instance = null;

    private EntrepreneurAccountControl() {
    	/*Default constructor*/
    }

    public static EntrepreneurAccountControl getInstance() {
        if(instance == null) {
        	instance = new EntrepreneurAccountControl();
        }

        return instance;
    }

	public List<BusinessInCountryBean> retrieveFavourites() throws DatabaseFailureException {
		BusinessInCountry business = new BusinessInCountry();
    	List<BusinessInCountry> list;
		try {
			list = business.getFavourites(SessionFacade.getSession().getID());
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}
    	
		List<BusinessInCountryBean> dest = new ArrayList<>();
    	for(BusinessInCountry i: list) {
    		BusinessInCountryBean bean = new BusinessInCountryBean();  		
    		
    		bean.setName(i.getName());
    		bean.setDescription(i.getDescription());
    		bean.setCategory(i.getCategory());
    		
    		CountryBean country = new CountryBean();
    		country.setName(i.getCountry().getName());
    		bean.setCountry(country);
    		
    		dest.add(bean);
    	}
    	
    	return dest;
	}
}
