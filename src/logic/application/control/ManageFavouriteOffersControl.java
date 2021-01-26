package logic.application.control;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.application.SessionFacade;
import logic.domain.Offer;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.bean.OfferBean;
import logic.service.Entity;
import logic.service.Factory;
import logic.service.OfferFactory;
import logic.service.Types;

public class ManageFavouriteOffersControl {

	private static ManageFavouriteOffersControl instance = null;

    private ManageFavouriteOffersControl() {
    	/*Singleton*/
    }

    public static ManageFavouriteOffersControl getInstance() {
        if(instance == null) {
        	instance = new ManageFavouriteOffersControl();
        }

        return instance;
    }

	public List<OfferBean> retrieveFavourites() throws DatabaseFailureException {
		Entity factory = Factory.getInstance().getObject(Types.OFFER);
    	Offer offer = (Offer)factory.createObject();
		try {
			List<Offer> list = offer.getFavourites(SessionFacade.getSession().getID().intValue());
			return ((OfferFactory)factory).extractOfferBeanList(list);
		} catch (SQLException se) {
			Logger.getLogger(ManageFavouriteOffersControl.class.getName()).log(Level.SEVERE, null, se);
			throw new DatabaseFailureException();
		}
	}

	public void addNewFavourite(int id) throws DatabaseFailureException {
		Entity factory = Factory.getInstance().getObject(Types.OFFER);
    	Offer offer = (Offer)factory.createObject();
    	offer.setId(id);
		try {
			offer.addFavourite(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			Logger.getLogger(ManageFavouriteOffersControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
	}

	public void removeFavourites(int id) throws DatabaseFailureException {
		Entity factory = Factory.getInstance().getObject(Types.OFFER);
    	Offer offer = (Offer)factory.createObject();
    	offer.setId(id);
		try {
			offer.deleteFavourite(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			Logger.getLogger(ManageFavouriteOffersControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
	}
}
