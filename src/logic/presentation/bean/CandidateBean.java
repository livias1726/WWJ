package logic.presentation.bean;

public class CandidateBean {
	
	private int idOffCand;
	private String nameCand;
	private Long idCand;
	
	public int getOffer() {
		return idOffCand;
	}

	public void setOffer(int offer) {
		this.idOffCand = offer;
	}

	public String getName() {
		return nameCand;
	}

	public void setName(String name) {
		this.nameCand = name;
	}

	public Long getSeeker() {
		return idCand;
	}

	public void setSeeker(Long seeker) {
		this.idCand = seeker;
	}
}
