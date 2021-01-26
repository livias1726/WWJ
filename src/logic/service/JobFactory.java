package logic.service;

import java.util.ArrayList;
import java.util.List;

import logic.domain.Job;
import logic.presentation.bean.JobBean;

public class JobFactory implements Entity{

	@Override
    public Job createObject() {
    	return new Job();
    }
    
    public List<JobBean> extractToBean(List<Job> src){
    	List<JobBean> dest = new ArrayList<>();
    	for(Job i: src) {
			JobBean bean = new JobBean();
			bean.setId(i.getId());
			bean.setName(i.getName());
			bean.setCategory(i.getCategory());
			
			dest.add(bean);
		}
    	return dest;
    }
}
