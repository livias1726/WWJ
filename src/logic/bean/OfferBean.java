package logic.bean;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.List;

import logic.application.control.ViewOfferControl;

public class OfferBean {

	private String companyName;
	private Array workingTimeSlot;
	private AddressBean branch;
	private LocalDate expiration;
	private LocalDate upload;
	private String taskDescription;
	private JobBean position;
	private List<RequirementBean> requirements;
	private float baseSalary;
	
	public OfferBean() {
		/*Constructor*/
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public Array getWorkingTimeSlot() {
		return workingTimeSlot;
	}

	public void setWorkingTimeSlot(Array workingTimeSlot) {
		this.workingTimeSlot = workingTimeSlot;
	}

	public AddressBean getBranch() {
		return branch;
	}

	public void setBranch(AddressBean branch) {
		this.branch = branch;
	}

	public LocalDate getExpiration() {
		return expiration;
	}

	public void setUpload(LocalDate upload) {
		this.upload = upload;
	}
	
	public LocalDate getUpload() {
		return upload;
	}

	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public JobBean getPosition() {
		return position;
	}

	public void setPosition(JobBean position) {
		this.position = position;
	}

	public List<RequirementBean> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<RequirementBean> requirements) {
		this.requirements = requirements;
	}

	public float getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(float baseSalary) {
		this.baseSalary = baseSalary;
	}
	
	public List<OfferBean> getOffers(CountryBean country, JobBean job){
		ViewOfferControl controller = ViewOfferControl.getInstance();
		return controller.retrieveOffers(country, job);
	}
	
	public List<OfferBean> getOffers(CountryBean country){
		ViewOfferControl controller = ViewOfferControl.getInstance();
		return controller.retrieveOffersByCountry(country);
	}
	
	public List<OfferBean> getOffers(JobBean job){
		ViewOfferControl controller = ViewOfferControl.getInstance();
		return controller.retrieveOffersByJob(job);
	}
}
