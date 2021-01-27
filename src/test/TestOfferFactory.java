package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import logic.domain.Offer;
import logic.presentation.bean.AddressBean;
import logic.presentation.bean.JobBean;
import logic.presentation.bean.OfferBean;
import logic.service.OfferFactory;

public class TestOfferFactory {

	@Test
	public void testExtractOfferStartFormat() {
		OfferBean offer = new OfferBean();
		
		setUp(offer);
    	
    	OfferFactory fact = new OfferFactory();
    	Offer ent = fact.extractOffer(offer);
		
    	String toTest;
    	if(offer.getStart().length() == 4) {
    		toTest = "0" + offer.getStart();
    	}else {
    		toTest = offer.getStart();
    	}
    	
		assertEquals(ent.getStart().toString(), toTest);
	}
	
	@Test
	public void testExtractOfferExpirationFormat() {
		OfferBean offer = new OfferBean();
		
    	setUp(offer);
    	
    	OfferFactory fact = new OfferFactory();
    	Offer ent = fact.extractOffer(offer);
		assertEquals(ent.getExpiration().toString(), offer.getExpiration());
	}

	private void setUp(OfferBean offer) {
		JobBean job = new JobBean();
    	job.setId(0);
    	offer.setPosition(job);
    	
    	offer.setTaskDescription("");
    	
    	AddressBean branch = new AddressBean();
    	branch.setId(0);
    	offer.setBranch(branch);
    	
    	offer.setStart("9:00");
    	offer.setFinish("16:00");
    	offer.setBaseSalary("");
    	offer.setExpiration("2021-02-12");
    	
    	List<String> requirements = new ArrayList<>();	
		offer.setRequirements(requirements);
	}
}
