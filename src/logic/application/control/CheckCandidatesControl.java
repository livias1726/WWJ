package logic.application.control;

import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

	public ObservableList<CandidateBean> retrieveCandidates() throws DatabaseFailureException {
		Candidate cand = new Candidate();
		List<Candidate> list;
		try {
			list = cand.getCandidatesFromDB(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException(); 
		}
		
		ObservableList<CandidateBean> dest = FXCollections.observableArrayList();
		
		for(Candidate i: list) {
			CandidateBean bean = new CandidateBean();
			
			bean.setOffer(i.getOffer());
			bean.setSeeker(i.getSeeker());
			bean.setName(i.getName());
			
			dest.add(bean);
		}
		
		return dest;
	}

	public void removeCandidates(List<Long> selected) throws DatabaseFailureException {
		Candidate cand = new Candidate();
		try {
			cand.removeCandidatesFromDB(selected);
		} catch (SQLException e) {
			throw new DatabaseFailureException(); 
		}
	}
}
