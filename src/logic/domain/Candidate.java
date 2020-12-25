package logic.domain;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import logic.persistence.dao.CandidateDAO;

public class Candidate implements Serializable{

	private static final long serialVersionUID = 7144303439707464129L;
	
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


	public List<Candidate> getCandidatesFromDB(Long id) throws SQLException {
		return CandidateDAO.selectCandidates(id);
	}

}
