package logic.domain;

import java.util.List;

public class RecruiterAccount extends Account {

	private static final long serialVersionUID = -251580676478529934L;
	
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

}
