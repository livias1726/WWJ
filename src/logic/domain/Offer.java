package logic.domain;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import logic.exceptions.NoResultFoundException;
import logic.persistence.dao.OfferDAO;

public class Offer implements Serializable{
	
	private static final long serialVersionUID = -764074719381746296L;
	
	protected int id;
	private String companyName;
	private Job position;
	private String taskDescription;
	private List<String> requirements;
	private Address branch;
	private LocalTime start;
	private LocalTime finish;
	private String baseSalary;
	private LocalDate expiration;
	private LocalDate upload;
	private int candidates;
	
	public Offer() {
		/**/
	}
	
	public Offer(int id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Address getBranch() {
		return branch;
	}

	public void setBranch(Address branch) {
		this.branch = branch;
	}

	public LocalDate getExpiration() {
		return expiration;
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

	public Job getPosition() {
		return position;
	}

	public void setPosition(Job position) {
		this.position = position;
	}
	
	public String getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(String baseSalary) {
		this.baseSalary = baseSalary;
	}

	public LocalDate getUpload() {
		return upload;
	}

	public void setUpload(LocalDate upload) {
		this.upload = upload;
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

	public List<String> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<String> requirements) {
		this.requirements = requirements;
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

	public List<Offer> getOffersByPosition(String job) throws NoResultFoundException, SQLException{
		return OfferDAO.selectByJob(job);
	}
	
	public List<Offer> getOffersByPlace(String country) throws NoResultFoundException, SQLException{
		return OfferDAO.selectByPlace(country);
	}
	
	public List<Offer> getOffers(String country, String job) throws NoResultFoundException, SQLException{
		return OfferDAO.selectOffers(country, job);
	}

	public List<Offer> getOffersByRecruiter(Long id) throws SQLException {
		return OfferDAO.selectPublishedOffers(id);
	}

	public Offer getOffer(Integer id) throws SQLException {
		return OfferDAO.selectOffer(id);
	}

	public List<Offer> getFavourites(int id) throws SQLException {
		return OfferDAO.selectFavourites(id);
	}

	public void addFavourite(Long id) throws SQLException {
		OfferDAO.insertIntoFavourite(this.id, id);
	}

	public void deleteFavourite(Long id) throws SQLException {
		OfferDAO.deleteFromFavourite(this.id, id);
	}

	public void publish(Long id) throws SQLException {
		OfferDAO.insertNewOffer(this, id);
	}
	
}
