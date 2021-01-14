package logic.presentation.bean;

public class CountryBean {
	
	private String name;
	private String currency;
	private Float livingExpense;
	private String exampleCity;
		
	public Float getLivingExpense() {
		return livingExpense;
	}

	public void setLivingExpense(Float livingExpense) {
		this.livingExpense = livingExpense;
	}

	public String getExampleCity() {
		return exampleCity;
	}

	public void setExampleCity(String exampleCity) {
		this.exampleCity = exampleCity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}