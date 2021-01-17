package logic.service;

import java.util.ArrayList;
import java.util.List;

import logic.domain.Application;
import logic.presentation.bean.ApplicationBean;
import logic.presentation.bean.JobBean;

public class ApplicationFactory implements AbstractFactory {

	@Override
    public Application createObject() {
    	return new Application();
    }
	
	public List<ApplicationBean> extractApplicationBeanList(List<Application> src){
		List<ApplicationBean> dest = new ArrayList<>();
		for(Application i: src) {
			ApplicationBean bean = new ApplicationBean();
			bean.setId(i.getId());
			
			JobBean job = new JobBean();
			job.setName(i.getPosition().getName());
			bean.setPosition(job);
			
			bean.setApplication(i.getApplication());
			bean.setExpiration(i.getExpiration());
			
			dest.add(bean);
		}
		return dest;
	}
	
}
