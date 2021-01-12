package logic.application.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.application.SessionFacade;
import logic.domain.Offer;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.bean.AddressBean;
import logic.presentation.bean.CountryBean;
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
    		offerBean.setId(i.getId());
    		offerBean.setCompanyName(i.getCompanyName());

    		JobBean job = new JobBean();
    		job.setName(i.getPosition().getName());
    		job.setCategory(i.getPosition().getCategory());  		
    		offerBean.setPosition(job);
    		
    		AddressBean branch = new AddressBean();
    		CountryBean country = new CountryBean();
    		country.setName(i.getBranch().getCountry().getName());
    		branch.setCountry(country);
    		branch.setState(i.getBranch().getState());
    		branch.setCity(i.getBranch().getCity());
    		offerBean.setBranch(branch);
    		
    		offerBean.setUpload(i.getUpload());
    		offerBean.setExpiration(i.getExpiration());
    		
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
