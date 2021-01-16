package logic.application.control;

import java.sql.SQLException;
import java.util.List;

import logic.application.SessionFacade;
import logic.domain.BusinessInCountry;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.bean.BusinessInCountryBean;
import logic.service.BusinessFactory;

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
		try {
			List<BusinessInCountry> list = BusinessFactory.getInstance().createBusiness().getFavourites(SessionFacade.getSession().getID());
			return BusinessFactory.getInstance().extractBusinessInCountryBeanList(list);
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}
	}

	public void addNewFavourite(int id) throws DatabaseFailureException {
		try {
			BusinessFactory.getInstance().createBusiness(id).addFavourite(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}

	public void removeFavourite(int id) throws DatabaseFailureException {
		try {
			BusinessFactory.getInstance().createBusiness(id).deleteFavourite(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}
}