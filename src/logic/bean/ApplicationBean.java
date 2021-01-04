package logic.bean;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import logic.application.control.ApplyToOfferControl;
import logic.application.control.SeekerAccountControl;
import logic.exceptions.DatabaseFailureException;

public class ApplicationBean extends OfferBean{
	
	private LocalDate application;
	
	public LocalDate getApplication() {
		return application;
	}

	public void setApplication(LocalDate application) {
		this.application = application;
	}

	public ObservableList<ApplicationBean> getApplications() throws DatabaseFailureException {
		return SeekerAccountControl.getInstance().retrieveApplications();
	}

	public void deleteSelectedApplications(List<Integer> selected) throws DatabaseFailureException {
		SeekerAccountControl.getInstance().removeApplications(selected);
	}

	public void addToApplications() throws DatabaseFailureException {
		ApplyToOfferControl.getInstance().apply(this); 
	}

}
