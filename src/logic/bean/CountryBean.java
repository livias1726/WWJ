package logic.bean;




import javafx.collections.ObservableList;
import logic.application.CountryControl;
import logic.exceptions.InvalidFieldException;

public class CountryBean {
	private String nameCountry;
	private String job;
	
	public String getNameCountry() {
		return nameCountry;
	}

	public void setNameCountry(String nameCountry) {
		this.nameCountry = nameCountry;
	}
	
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public ObservableList<String> getCountries(){
		CountryControl control = CountryControl.getInstance();
		return control.retrieveCountries();
	}
	
	public boolean checkFieldValidity(String str) {
		return (str == null || str.equals(""));
	}
	
	public boolean verifyJobSeekerResearch() throws InvalidFieldException{
        if (checkFieldValidity(nameCountry) || checkFieldValidity(job)) {
            return false;
        }

        if(CountryControl.getInstance().jobSeekerResearch(nameCountry, job)) {
        	return true;
        }else{
        	throw new InvalidFieldException("Research has failed. Retry.");
        }
    }

	
}
