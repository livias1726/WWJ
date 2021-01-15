package logic.presentation.bean;

import java.util.List;

public class BusinessInCountryBean extends BusinessBean{

	private Float averageCostBean;
	private Float averageEarningsBean;
	private List<Float> pop;
	private List<Integer> comp;
	private List<Float> taxes;
	private String descBusBean;
	private Float startExpense;
	private CountryBean country;
	
	public Float getAverageCost() {
		return averageCostBean;
	}

	public void setAverageCost(Float averageCost) {
		this.averageCostBean = averageCost;
	}

	public List<Float> getPopularity() {
		return pop;
	}

	public void setPopularity(List<Float> popularity) {
		this.pop = popularity;
	}

	public List<Integer> getCompetitors() {
		return comp;
	}

	public void setCompetitors(List<Integer> competitors) {
		this.comp = competitors;
	}

	public Float getAverageEarnings() {
		return averageEarningsBean;
	}

	public void setAverageEarnings(Float averageEarnings) {
		this.averageEarningsBean = averageEarnings;
	}

	public String getDescription() {
		return descBusBean;
	}

	public void setDescription(String description) {
		this.descBusBean = description;
	}

	public CountryBean getCountry() {
		return country;
	}

	public void setCountry(CountryBean country) {
		this.country = country;
	}
	
	public Float getStartExpense() {
		return startExpense;
	}

	public void setStartExpense(Float startExpense) {
		this.startExpense = startExpense;
	}
	
	public List<Float> getTaxes() {
		return taxes;
	}

	public void setTaxes(List<Float> taxes) {
		this.taxes = taxes;
	}
}
