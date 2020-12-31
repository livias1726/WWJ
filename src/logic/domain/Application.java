package logic.domain;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import logic.persistence.dao.ApplicationDAO;

public class Application extends Offer{

	private static final long serialVersionUID = 3765251218779132050L;
	private LocalDate appDate;
	
	public LocalDate getApplication() {
		return appDate;
	}

	public void setApplication(LocalDate application) {
		this.appDate = application;
	}

	public List<Application> getApplications(Long id) throws SQLException {
		return ApplicationDAO.selectSeekersApplications(id.toString());
	}

	public void removeApplicatinosFromDB(List<Integer> selected) throws SQLException {
		ApplicationDAO.deleteSeekersApplications(selected);
	}

}
