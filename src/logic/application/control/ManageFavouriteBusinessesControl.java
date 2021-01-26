package logic.application.control;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.application.SessionFacade;
import logic.domain.BusinessInCountry;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.bean.BusinessInCountryBean;
import logic.service.Entity;
import logic.service.BusinessInCountryFactory;
import logic.service.Factory;
import logic.service.Types;

public class ManageFavouriteBusinessesControl {

	private static ManageFavouriteBusinessesControl instance = null;

    private ManageFavouriteBusinessesControl() {
    	/*Singleton*/
    }

    public static ManageFavouriteBusinessesControl getInstance() {
        if(instance == null) {
        	instance = new ManageFavouriteBusinessesControl();
        }

        return instance;
    }
    
    public List<BusinessInCountryBean> retrieveFavourites() throws DatabaseFailureException {
    	Entity factory = Factory.getInstance().getObject(Types.BUSINESSINCOUNTRY);
    	BusinessInCountry bus = (BusinessInCountry)factory.createObject();
    	try {
			List<BusinessInCountry> list = bus.getFavourites(SessionFacade.getSession().getID());
			return ((BusinessInCountryFactory)factory).extractBusinessInCountryBeanList(list);
		} catch (SQLException se) {
			Logger.getLogger(ManageFavouriteBusinessesControl.class.getName()).log(Level.SEVERE, null, se);
			throw new DatabaseFailureException();
		}
	}

	public void addNewFavourite(int id) throws DatabaseFailureException {
		Entity factory = Factory.getInstance().getObject(Types.BUSINESSINCOUNTRY);
    	BusinessInCountry bus = (BusinessInCountry)factory.createObject();
    	bus.setId(id);
		try {
			bus.addFavourite(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			Logger.getLogger(ManageFavouriteBusinessesControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
	}

	public void removeFavourite(int id) throws DatabaseFailureException {
		Entity factory = Factory.getInstance().getObject(Types.BUSINESSINCOUNTRY);
    	BusinessInCountry bus = (BusinessInCountry)factory.createObject();
    	bus.setId(id);
		try {
			bus.deleteFavourite(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			Logger.getLogger(ManageFavouriteBusinessesControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
	}
}
