package logic.persistence;

public class RoutinesIdentifier {
	
	private RoutinesIdentifier() {
		/**/
	}
	
	public static final String FETCH_USER = "call retrieve_user(?)";
	public static final String FETCH_ACCOUNT = "call retrieve_account(?)";
	public static final String GET_NOTIFICATIONS = "call retrieve_notifications(?)";
	public static final String LOGIN = "call login(?, ?)";
	public static final String SIGNUP = "call add_user(?, ?, ?, ?, ?)";
	public static final String FETCH_COMPANY = "call retrieve_company(?)";
	public static final String GET_OFFERS_BY_COUNTRY = "call retrieve_offers_by_country(?)";
	public static final String GET_OFFERS = "call retrieve_offers(?, ?)";
	public static final String GET_OFFERS_BY_JOB = "call retrieve_offers_by_job(?)";
	public static final String GET_RECRUITERS_OFFERS = "call retrieve_recruiter_offers(?)";
	public static final String GET_OFFER = "call retrieve_offer(?)";
	public static final String GET_CANDIDATES = "call retrieve_candidates(?)";
}
