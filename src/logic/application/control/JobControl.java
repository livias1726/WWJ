package logic.application.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.domain.Job;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.bean.JobBean;

public class JobControl {

	private static JobControl instance = null;

    private JobControl() {
    	/*Default constructor*/
    }

    public static JobControl getInstance() {
        if(instance == null) {
        	instance = new JobControl();
        }

        return instance;
    }

    public List<JobBean> retrieveJobs() throws DatabaseFailureException {
		Job job = new Job();
    	List<JobBean> jobs = new ArrayList<>();
    	
    	try {
			for(Job i: job.getAvailableJobs()) {
				JobBean bean = new JobBean();
				bean.setId(i.getId());
				bean.setName(i.getName());
				bean.setCategory(i.getCategory());
				
				jobs.add(bean);
			}
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
    	
    	return jobs;
	}
    
    public void saveNewJob(JobBean bean) throws DatabaseFailureException {
		Job job = new Job(bean.getName(), bean.getCategory());
		try {
			job.addJobToDB();
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}
}
