package logic.persistence;

public class RoutinesIdentifier {
	
	private RoutinesIdentifier() {
		/**/
	}
	
	public static final String LOGIN = "call login(?, ?)";
	public static final String SIGNUP = "call add_user(?, ?, ?, ?, ?)";
	public static final String GET_USER = "call retrieve_user(?)";
	public static final String GET_ACCOUNT = "call retrieve_account(?)";
	public static final String GET_NOTIFICATIONS = "call retrieve_notifications(?)";
	public static final String GET_COMPANY = "call retrieve_company(?)";
	public static final String GET_OFFERS_BY_COUNTRY = "call retrieve_offers_by_country(?)";
	public static final String GET_OFFERS = "call retrieve_offers(?, ?)";
	public static final String GET_OFFERS_BY_JOB = "call retrieve_offers_by_job(?)";
	public static final String GET_RECRUITERS_OFFERS = "call retrieve_recruiter_offers(?)";
	public static final String GET_OFFER = "call retrieve_offer(?)";
	public static final String GET_CANDIDATES = "call retrieve_candidates(?)";
	public static final String UPDATE_USER = "call update_user(?,?,?,?,?,?,?)";
	public static final String UPDATE_COMPANY = "call update_company(?,?,?,?,?,?,?)";
	public static final String UPDATE_BRANCHES = "call update_address(?,?)";
	public static final String GET_COUNTRIES = "call retrieve_countries()";
	public static final String GET_JOBS = "call retrieve_jobs()";
	public static final String GET_BUSINESSES = "call retrieve_businesses()";
	public static final String GET_BUSINESS_IN_COUNTRY = "call retrieve_business_in_country(?, ?)";
	public static final String GET_BUSINESS_BY_COUNTRY = "call retrieve_business_by_country(?)";
	public static final String GET_BUSINESS_BY_NAME = "call retrieve_business_by_name(?)";
	public static final String GET_FAVOURITE_BUSINESSES = "call retrieve_favourite_businesses(?)";
	public static final String GET_APPLICATIONS = "call retrieve_applications(?)";
	public static final String DELETE_CANDIDATES = "call delete_candidate(?)";
	public static final String DELETE_APPLICATIONS = "call delete_application(?)";
}
