package logic.presentation.bean;

public class BusinessBean {
	
	protected int idBus;
	protected String catBus;
	protected String nameBus;
		
	public int getId() {
		return idBus;
	}

	public void setId(int id) {
		this.idBus = id;
	}

	public String getName() {
		return nameBus;
	}

	public void setName(String name) {
		this.nameBus = name;
	}
	
	public String getCategory() {
		return catBus;
	}

	public void setCategory(String category) {
		this.catBus = category;
	}
}
