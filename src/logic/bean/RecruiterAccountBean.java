package logic.bean;

import java.util.List;

import logic.application.RecruiterAccountControl;
import logic.domain.Candidate;
import logic.domain.Company;
import logic.domain.Offer;

public class RecruiterAccountBean extends AccountBean {
	
	private Company company;
	private List<Offer> offers;
	private List<Candidate> candidates;
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public List<Offer> getOffers() {
		return offers;
	}
	
	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
	
	public List<Candidate> getCandidates() {
		return candidates;
	}
	
	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}
	
	public RecruiterAccountBean retrieveRecInfo() {
		return RecruiterAccountControl.getInstance().retrieveRecAccount();
	}

}
