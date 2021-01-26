package logic.service;

import java.util.ArrayList;
import java.util.List;

import logic.domain.Candidate;
import logic.presentation.bean.CandidateBean;

public class CandidateFactory implements Entity {

	@Override
    public Candidate createObject() {
    	return new Candidate();
    }
	
	public List<CandidateBean> extractCandidateBeanList(List<Candidate> src){
		List<CandidateBean> dest = new ArrayList<>();
		for(Candidate i: src) {
			CandidateBean bean = new CandidateBean();
			
			bean.setOffer(i.getOffer());
			bean.setSeeker(i.getSeeker());
			bean.setName(i.getName());
			
			dest.add(bean);
		}
		
		return dest;
	}
}
