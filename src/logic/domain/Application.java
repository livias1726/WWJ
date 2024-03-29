package logic.domain;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import logic.persistence.dao.ApplicationDAO;

public class Application extends Offer{

	private static final long serialVersionUID = 3765251218779132050L;
	private LocalDate appDate;
	
	public Application() {
		/**/
	}
	
	public Application(int id) {
		super.id = id;
	}

	public LocalDate getApplication() {
		return appDate;
	}

	public void setApplication(LocalDate application) {
		this.appDate = application;
	}

	public List<Application> getApplications(Long id) throws SQLException {
		return ApplicationDAO.selectSeekersApplications(id);
	}

	public void removeApplicatinosFromDB(Long id, List<Integer> selected) throws SQLException {
		ApplicationDAO.deleteSeekersApplications(id, selected);
	}

	public void addApplication(Long id) throws SQLException {
		ApplicationDAO.insertSeekersApplication(this.id, id);
	}

}
