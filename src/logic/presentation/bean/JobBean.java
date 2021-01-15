package logic.presentation.bean;

import logic.exceptions.InvalidFieldException;

public class JobBean {

	private int idJob;
	private String nameJob;
	private String catJob;
	
	public int getId() {
		return idJob;
	}

	public void setId(int id) {
		this.idJob = id;
	}

	public String getName() {
		return nameJob;
	}
	
	public void setName(String name) {
		this.nameJob = name;
	}
	
	public String getCategory() {
		return catJob;
	}
	
	public void setCategory(String category) {
		this.catJob = category;
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
