package logic.domain;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Offer implements Serializable{
	
	private static final long serialVersionUID = -764074719381746296L;
	
	private String companyName;
	private Array workingTimeSlot;
	private Address branch;
	private LocalDate expiration;
	private LocalDate upload;
	private String taskDescription;
	private Job position;
	private List<Requirement> requirements;
	private float baseSalary;
	
	public Offer(String branch, String position) {
		Job job = new Job();
		job.setName(position);
		
		Address add = new Address();
		add.setState(branch);
		
		this.position = job;
		this.branch = add;
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

	public List<Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
	}

	public float getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(float baseSalary) {
		this.baseSalary = baseSalary;
	}

	public List<Offer> getOffersByPosition(){
		/**DAO INTERACTION*/
		
		/*DUMMY BEHAVIOR*/
		List<Offer> list = new ArrayList<>();
		String job = "Software Engineer";
		list.add(new Offer("Canada", job));
		list.add(new Offer("Germany", job));
		list.add(new Offer("California, USA", job));
		return list;
	}
	
	public List<Offer> getOffersByPlace(){
		/**DAO INTERACTION*/
		
		/*DUMMY BEHAVIOR*/
		List<Offer> list = new ArrayList<>();
		String country = "Canada";
		list.add(new Offer(country, "Software Engineer"));
		list.add(new Offer(country, "Chemist"));
		list.add(new Offer(country, "Psychiatrist"));
		return list;
	}
	
	public List<Offer> getOffers(){
		/**DAO INTERACTION*/
		
		/*DUMMY BEHAVIOR*/
		List<Offer> list = new ArrayList<>();		
		list.add(new Offer("Canada", "Software Engineer"));
		return list;
	}

}
