package logic.bean;

import java.util.List;

import logic.application.control.CheckCandidatesControl;
import logic.exceptions.DatabaseFailureException;

public class CandidateBean {
	
	private int offer;
	private String name;
	private int seeker;
	
	public int getOffer() {
		return offer;
	}

	public void setOffer(int offer) {
		this.offer = offer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeeker() {
		return seeker;
	}

	public void setSeeker(int seeker) {
		this.seeker = seeker;
	}
	
	public List<CandidateBean> getCandidates() throws DatabaseFailureException {
		return CheckCandidatesControl.getInstance().retrieveCandidates();
	}

	public void deleteSelectedCandidates(List<Long> selected) throws DatabaseFailureException {
		CheckCandidatesControl.getInstance().removeCandidates(selected);
	}
}
