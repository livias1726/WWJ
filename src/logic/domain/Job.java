package logic.domain;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import logic.persistence.dao.JobDAO;

public class Job implements Serializable{

	private static final long serialVersionUID = 7555734428882964601L;
	private String name;
	private String category;
	
	public Job() {
		/**/
	}
	
	public Job(String name, String category) {
		this.name = name;
		this.category = category;				
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Job> getAvailableJobs() throws SQLException{
		return JobDAO.selectJobs();
	}

	public void addJobToDB() throws SQLException {
		JobDAO.insertJob(name, category);
	}

}
