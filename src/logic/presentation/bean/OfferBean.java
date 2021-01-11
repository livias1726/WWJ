package logic.presentation.bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javafx.collections.ObservableList;
import logic.application.control.FavouriteOffersControl;
import logic.application.control.ViewOfferControl;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.InvalidFieldException;
import logic.exceptions.NoResultFoundException;

public class OfferBean {

	private int id;
	private String companyName;
	
	private JobBean position;
	private String jobName; //used for table views
	
	private String taskDescription;
	private List<String> requirements;
	private AddressBean branch;
	private LocalTime start;
	private LocalTime finish;
	private String baseSalary;
	private LocalDate expiration;
	private LocalDate upload;
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

	public AddressBean getBranch() {
		return branch;
	}

	public void setBranch(AddressBean branch) {
		this.branch = branch;
	}

	public LocalTime getStart() {
		return start;
	}

	public void setStart(LocalTime start) {
		this.start = start;
	}

	public LocalTime getFinish() {
		return finish;
	}

	public void setFinish(LocalTime finish) {
		this.finish = finish;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public ObservableList<OfferBean> getOffers(CountryBean country, JobBean job) throws DatabaseFailureException, NoResultFoundException{
		return ViewOfferControl.getInstance().retrieveOffers(country, job);
	}
	
	public ObservableList<OfferBean> getOffers(CountryBean country) throws DatabaseFailureException, NoResultFoundException{
		return ViewOfferControl.getInstance().retrieveOffersByCountry(country);
	}
	
	public ObservableList<OfferBean> getOffers(JobBean job) throws DatabaseFailureException, NoResultFoundException{
		return ViewOfferControl.getInstance().retrieveOffersByJob(job);
	}

	public List<OfferBean> getFavouriteOffers() throws DatabaseFailureException {
		return FavouriteOffersControl.getInstance().retrieveFavourites();
	}

	public void addToFavourites() throws DatabaseFailureException {
		FavouriteOffersControl.getInstance().addNewFavourite(this.id);
	}

	public void removeFromFavourites() throws DatabaseFailureException {
		FavouriteOffersControl.getInstance().removeFavourites(this.id);
	}
	
	public void verifyFieldsValidity(LocalTime start, LocalTime finish, LocalDate expiration) throws InvalidFieldException {
		if(start != null) {
			if(finish == null) {
				throw new InvalidFieldException("Please, provide a 'Finish' time or remove alse the 'Start' one");
			}else {
				if(start.isAfter(finish)) {
					throw new InvalidFieldException("The 'Finish' time must be after the 'Start' time");
				}
			}
		}
		
		if(start == null && finish != null) {
			throw new InvalidFieldException("Please, provide a 'Start' time or remove alse the 'Finish' one");
		}
	
		if(expiration.isBefore(LocalDate.now())) {
			throw new InvalidFieldException("The Expiration date must be after in the future");
		}
	}
}
