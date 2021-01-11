package logic.application.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.application.SessionFacade;
import logic.domain.Offer;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.bean.JobBean;
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
		Offer offer = new Offer();
    	List<Offer> list;
		try {
			list = offer.getFavourites(SessionFacade.getSession().getID().intValue());
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}
    	
		List<OfferBean> dest = new ArrayList<>();
    	for(Offer i: list) {
    		OfferBean offerBean = new OfferBean();  		
    		offerBean.setCompanyName(i.getCompanyName());
    		offerBean.setTaskDescription(i.getTaskDescription());

    		JobBean job = new JobBean();
    		job.setName(i.getPosition().getName());
    		job.setCategory(i.getPosition().getCategory());  		
    		offerBean.setPosition(job);
    		
    		dest.add(offerBean);
    	}
    	
    	return dest;
	}

	public void addNewFavourite(int id) throws DatabaseFailureException {
		Offer fav = new Offer();
		fav.setId(id);
		try {
			fav.addFavourite(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}

	public void removeFavourites(int id) throws DatabaseFailureException {
		Offer fav = new Offer();
		fav.setId(id);
		try {
			fav.deleteFavourite(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}
}
