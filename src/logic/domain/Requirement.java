package logic.domain;

import java.util.List;

public class Requirement {
	
	private List<Language> lang;
	private List<Education> edu;
	private List<Experience> exp;
	
	public List<Language> getLang() {
		return lang;
	}
	
	public void setLang(List<Language> lang) {
		this.lang = lang;
	}
	
	public List<Education> getEdu() {
		return edu;
	}
	
	public void setEdu(List<Education> edu) {
		this.edu = edu;
	}
	
	public List<Experience> getExp() {
		return exp;
	}
	
	public void setExp(List<Experience> exp) {
		this.exp = exp;
	}
}
