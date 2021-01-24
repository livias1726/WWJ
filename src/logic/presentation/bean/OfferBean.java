package logic.presentation.bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import logic.exceptions.InvalidFieldException;

public class OfferBean {

	private int id;
	private String companyName;
	
	private JobBean position;
	private String jobName; //used for table views
	
	private String taskDescription;
	private List<String> requirements;
	private String requirements2;
	private AddressBean branch;
	private String start;
	private String finish;
	private String baseSalary;
	private String expiration;
	private String upload;
	private int candidates;
	
	public JobBean getPosition() {
		return position;
	}

	public void setPosition(JobBean position) {
		this.position = position;
		if(position.getName() != null) {
			this.jobName = position.getName();
		}
	}
	
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public List<String> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<String> requirements) {
		this.requirements = requirements;
	}
	
	
	public String getRequirements2() {
		return requirements2;
	}
	
	public void setRequirements2(String requirements2) {
		this.requirements2 = requirements2;
	}

	public AddressBean getBranch() {
		return branch;
	}

	public void setBranch(AddressBean branch) {
		this.branch = branch;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(String baseSalary) {
		this.baseSalary = baseSalary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCandidates() {
		return candidates;
	}

	public void setCandidates(int candidates) {
		this.candidates = candidates;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}
	
	public void verifyFieldsValidity(String start, String finish, String expiration) throws InvalidFieldException {
		if(start != null) {
			if(finish == null) {
				throw new InvalidFieldException("Please, provide a 'Finish' time or remove also the 'Start' one");
			}else {
				if(LocalTime.parse(start).isAfter(LocalTime.parse(finish))) {
					throw new InvalidFieldException("The 'Finish' time must be after the 'Start' time");
				}
			}
		}
		
		if(start == null && finish != null) {
			throw new InvalidFieldException("Please, provide a 'Start' time or remove also the 'Finish' one");
		}
	
		if(LocalDate.parse(expiration).isBefore(LocalDate.now())) {
			throw new InvalidFieldException("The Expiration date must be in the future");
		}
	}
	
	public void convertCurrencyToStr() {
		String[] curr = this.getBaseSalary().split(" ");
		String tmp = null;
		switch(curr[0]) {
			case "$":
				tmp = "USD";
				break;
			case "€":
				tmp = "EUR";
				break;
			case "£":
				tmp = "GBP";
				break;
			default:
				break;
		}
		
		if(tmp != null) {
			setBaseSalary(tmp + " " + curr[1]);
		}
	}
	
	public void convertCurrencyToSym() {
		String[] curr = this.getBaseSalary().split(" ");
		String tmp = null;
		switch(curr[0]) {
			case "USD":
				tmp = "$";
				break;
			case "EUR":
				tmp = "€";
				break;
			case "GBP":
				tmp = "£";
				break;
			default:
				break;
		}
		
		if(tmp != null) {
			setBaseSalary(tmp + " " + curr[1]);
		}
	}
}
