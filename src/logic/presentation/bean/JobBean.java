package logic.presentation.bean;

import logic.exceptions.InvalidFieldException;

public class JobBean {

	private int id;
	private String name;
	private String category;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public boolean checkFieldValidity(String str) {
		return (str == null || str.equals(""));
	}

    public void verifyFields(String...params) throws InvalidFieldException{
    	for (int i = 0; i < params.length; i++) {
    		if (checkFieldValidity(params[i])) {
                throw new InvalidFieldException("Please, fill out every field");
            }
		}     
    }
}
