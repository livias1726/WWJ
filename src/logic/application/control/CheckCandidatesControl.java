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
import logic.service.AbstractFactory;
import logic.service.CandidateFactory;
import logic.service.Factory;
import logic.service.Types;

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
		AbstractFactory factory = Factory.getInstance().getObject(Types.CANDIDATE);
		Candidate cand = (Candidate)factory.createObject();
		
		try {
			List<Candidate> list = cand.getCandidatesFromDB(SessionFacade.getSession().getID());
			ObservableList<CandidateBean> dest = FXCollections.observableArrayList();
	    	for(CandidateBean i: ((CandidateFactory)factory).extractCandidateBeanList(list)) {
	    		dest.add(i);
	    	}   
	    	
	    	return dest;
		} catch (SQLException e) {
			Logger.getLogger(CheckCandidatesControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException(); 
		}		
	}

	public void removeCandidates(List<Long> candidates, List<Integer> offers) throws DatabaseFailureException {
		AbstractFactory factory = Factory.getInstance().getObject(Types.CANDIDATE);
		Candidate cand = (Candidate)factory.createObject();
		
		try {
			cand.removeCandidatesFromDB(candidates, offers);
		} catch (SQLException e) {
			Logger.getLogger(CheckCandidatesControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException(); 
		}
	}
}
