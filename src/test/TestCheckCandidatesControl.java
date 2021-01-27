package test;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import javafx.collections.ObservableList;
import logic.application.SessionFacade;
import logic.application.control.CheckCandidatesControl;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.bean.CandidateBean;

/**
 * @author dominique toce
 */

public class TestCheckCandidatesControl {

	@Test
	public void testRemoveCandidatesSuccess() throws DatabaseFailureException {
		SessionFacade.getSession().setID((long)1);
		
		ObservableList<CandidateBean> baseList = CheckCandidatesControl.getInstance().retrieveCandidates();
		
		int exp;
		
		if(!baseList.isEmpty()) {
			List<Long> selCand = new ArrayList<>();
			selCand.add(baseList.get(0).getSeeker());
			
			List<Integer> selOff = new ArrayList<>();
			selOff.add(baseList.get(0).getOffer());
			
			CheckCandidatesControl.getInstance().removeCandidates(selCand, selOff);
			
			exp = baseList.size() - 1;
		}else{
			exp = baseList.size();
		}
				
		ObservableList<CandidateBean> newList = CheckCandidatesControl.getInstance().retrieveCandidates();
		
		assertEquals(exp, newList.size());
	}
	
	@Test
	public void testSelectCandidatesEmptyNotNull() throws DatabaseFailureException {
		SessionFacade.getSession().setID((long)1);
		
		ObservableList<CandidateBean> list = CheckCandidatesControl.getInstance().retrieveCandidates();
		
		assertNotNull(list);
	}
	
	@Test
	public void testRemoveCandidatesNotFound() throws DatabaseFailureException {
		SessionFacade.getSession().setID((long)1);
		
		ObservableList<CandidateBean> baseList = CheckCandidatesControl.getInstance().retrieveCandidates();
		
		List<Long> cand = new ArrayList<>();
		cand.add((long) 0);
		
		List<Integer> off = new ArrayList<>();
		off.add(0);
		
		CheckCandidatesControl.getInstance().removeCandidates(cand, off);
				
		ObservableList<CandidateBean> newList = CheckCandidatesControl.getInstance().retrieveCandidates();
		
		assertEquals(baseList.size(), newList.size());
	}

}
