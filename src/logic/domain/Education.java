package logic.domain;

public class Education {
	private String subject;
	private int grade;
	private EducationType type;
	
	public EducationType getType() {
		return type;
	}

	public void setType(EducationType type) {
		this.type = type;
	}

	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	
}
