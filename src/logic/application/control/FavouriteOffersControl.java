package logic.application.control;

import java.sql.SQLException;
import java.util.List;

import logic.application.SessionFacade;
import logic.domain.Offer;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.bean.OfferBean;

public class FavouriteOffersControl {

	private static FavouriteOffersControl instance = null;

    private FavouriteOffersControl() {
    	/*Singleton*/
    }

    public static FavouriteOffersControl getInstance() {
        if(instance == null) {
        	instance = new FavouriteOffersControl();
        }

        return instance;
    }

	public List<OfferBean> retrieveFavourites() throws DatabaseFailureException {
		try {
			List<Offer> list = OfferFactory.getInstance().createOffer().getFavourites(SessionFacade.getSession().getID().intValue());
			return OfferFactory.getInstance().extractOfferBeanList(list);
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}
	}

	public void addNewFavourite(int id) throws DatabaseFailureException {
		try {
			OfferFactory.getInstance().createOffer(id).addFavourite(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}

	public void removeFavourites(int id) throws DatabaseFailureException {
		try {
			OfferFactory.getInstance().createOffer(id).deleteFavourite(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}
}
