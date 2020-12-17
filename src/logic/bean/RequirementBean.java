package logic.bean;

import java.util.List;

public class RequirementBean {
	
	private List<LanguageBean> lang;
	private List<EducationBean> edu;
	private List<ExperienceBean> exp;
	
	public List<LanguageBean> getLang() {
		return lang;
	}
	
	public void setLang(List<LanguageBean> lang) {
		this.lang = lang;
	}
	
	public List<EducationBean> getEdu() {
		return edu;
	}
	
	public void setEdu(List<EducationBean> edu) {
		this.edu = edu;
	}
	
	public List<ExperienceBean> getExp() {
		return exp;
	}
	
	public void setExp(List<ExperienceBean> exp) {
		this.exp = exp;
	}
}
