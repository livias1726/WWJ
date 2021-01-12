package logic.presentation.bean;

import java.time.LocalDate;

public class ApplicationBean extends OfferBean{
	
	private LocalDate application;
	
	public LocalDate getApplication() {
		return application;
	}

	public void setApplication(LocalDate application) {
		this.application = application;
	}
}
