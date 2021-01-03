package logic.application.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.application.SessionFacade;
import logic.bean.CandidateBean;
import logic.domain.Candidate;
import logic.exceptions.DatabaseFailureException;

public class CheckCandidatesControl {
	
	private static CheckCandidatesControl instance = null;

    private CheckCandidatesControl() {
    	/*Singleton*/
    }

    public static CheckCandidatesControl getInstance() {
        if(instance == null) {
        	instance = new CheckCandidatesControl();
        }

        return instance;
    }

	public List<CandidateBean> retrieveCandidates() throws DatabaseFailureException {
		Candidate cand = new Candidate();
		List<Candidate> list;
		try {
			list = cand.getCandidatesFromDB(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException(); 
		}
		
		List<CandidateBean> dest = new ArrayList<>();
		
		for(Candidate i: list) {
			CandidateBean bean = new CandidateBean();
			
			bean.setOffer(i.getOffer());
			bean.setSeeker(i.getSeeker());
			bean.setName(i.getName());
			
			dest.add(bean);
		}
		
		return dest;
	}

	public void removeCandidates(List<Integer> selected) throws DatabaseFailureException {
		Candidate cand = new Candidate();
		try {
			cand.removeCandidatesFromDB(selected);
		} catch (SQLException e) {
			throw new DatabaseFailureException(); 
		}
	}
}
