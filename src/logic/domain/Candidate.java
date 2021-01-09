package logic.domain;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import logic.persistence.dao.CandidateDAO;

public class Candidate implements Serializable{

	private static final long serialVersionUID = 7144303439707464129L;
	
	private int offer;
	private String name;
	private Long seeker;
	
	public Candidate() {
		/**/
	}
	
	public Candidate(Long id, int offer, String name) {
		this.offer = offer;
		this.seeker = id;
		this.name = name;
	}
	
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


	public List<Candidate> getCandidatesFromDB(Long id) throws SQLException {
		return CandidateDAO.selectCandidates(id);
	}

	public void removeCandidatesFromDB(List<Long> selected) throws SQLException {
		CandidateDAO.deleteCandidates(selected);
	}

}
