package logic.application.control;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.application.SessionFacade;
import logic.domain.Candidate;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.bean.CandidateBean;

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
			Logger.getLogger(CheckCandidatesControl.class.getName()).log(Level.SEVERE, null, e);
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

	public void removeCandidates(List<Long> candidates, List<Integer> offers) throws DatabaseFailureException {
		Candidate cand = new Candidate();
		try {
			cand.removeCandidatesFromDB(candidates, offers);
		} catch (SQLException e) {
			Logger.getLogger(CheckCandidatesControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException(); 
		}
	}
}
