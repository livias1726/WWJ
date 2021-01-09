package logic.bean;

import java.util.List;

import javafx.collections.ObservableList;
import logic.application.control.CheckCandidatesControl;
import logic.exceptions.DatabaseFailureException;

public class CandidateBean {
	
	private int offer;
	private String name;
	private Long seeker;
	
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

	public Long getSeeker() {
		return seeker;
	}

	public void setSeeker(Long seeker) {
		this.seeker = seeker;
	}
	
	public ObservableList<CandidateBean> getCandidates() throws DatabaseFailureException {
		return CheckCandidatesControl.getInstance().retrieveCandidates();
	}

	public void deleteSelectedCandidates(List<Long> selected) throws DatabaseFailureException {
		CheckCandidatesControl.getInstance().removeCandidates(selected);
	}
}
