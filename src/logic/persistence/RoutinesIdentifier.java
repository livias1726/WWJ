package logic.persistence;

public class RoutinesIdentifier {
	
	private RoutinesIdentifier() {
		/**/
	}
	
	public static final String LOGIN = "call wwj_db.login(?, ?)";
	public static final String SIGNUP = "call wwj_db.add_user(?, ?, ?, ?, ?)";
	public static final String GET_USER = "call wwj_db.retrieve_user(?)";
	public static final String GET_ACCOUNT = "call wwj_db.retrieve_account(?)";
	public static final String GET_NOTIFICATIONS = "call wwj_db.retrieve_notifications(?)";
	public static final String GET_COMPANY = "call wwj_db.retrieve_company(?)";
	public static final String GET_OFFERS_BY_COUNTRY = "call wwj_db.retrieve_offers_by_country(?)";
	public static final String GET_OFFERS = "call wwj_db.retrieve_offers(?, ?)";
	public static final String GET_OFFERS_BY_JOB = "call wwj_db.retrieve_offers_by_job(?)";
	public static final String GET_RECRUITERS_OFFERS = "call wwj_db.retrieve_recruiter_offers(?)";
	public static final String GET_OFFER = "call wwj_db.retrieve_offer(?)";
	public static final String GET_CANDIDATES = "call wwj_db.retrieve_candidates(?)";
	public static final String UPDATE_USER = "call wwj_db.update_user(?,?,?,?,?,?,?)";
	public static final String UPDATE_COMPANY = "call wwj_db.update_company(?,?,?)";
	public static final String UPDATE_BRANCHES = "call wwj_db.update_branch(?,?,?,?,?,?,?,?)";
	public static final String GET_COUNTRIES = "call wwj_db.retrieve_countries()";
	public static final String GET_JOBS = "call wwj_db.retrieve_jobs()";
	public static final String GET_BUSINESSES = "call wwj_db.retrieve_businesses()";
	public static final String GET_BUSINESS_IN_COUNTRY = "call wwj_db.retrieve_business_in_country(?, ?)";
	public static final String GET_BUSINESS_BY_COUNTRY = "call wwj_db.retrieve_business_by_country(?)";
	public static final String GET_BUSINESS_BY_NAME = "call wwj_db.retrieve_business_by_name(?)";
	public static final String GET_FAVOURITE_BUSINESSES = "call wwj_db.retrieve_favourite_businesses(?)";
	public static final String GET_APPLICATIONS = "call wwj_db.retrieve_applications(?)";
	public static final String DELETE_CANDIDATES = "call wwj_db.delete_candidate(?)";
	public static final String DELETE_APPLICATIONS = "call wwj_db.delete_application(?)";
	public static final String GET_CV = "call wwj_db.retrieve_curriculum(?)";
	public static final String UPDATE_CV = "call wwj_db.update_curriculum(?, ?)";
	public static final String GET_FAVOURITE_OFFERS = "call wwj_db.retrieve_favourite_offers(?)";
	public static final String INSERT_APPLICATION = "call wwj_db.insert_application(?, ?)";
	public static final String INSERT_FAVOURITE_OFFER = "call wwj_db.insert_favourite_offer(?, ?)";
	public static final String DELETE_FAVOURITE_OFFER = "call wwj_db.delete_favourite_offer(?, ?)";
	public static final String INSERT_FAVOURITE_BUSINESS = "call wwj_db.insert_favourite_business(?, ?)";
	public static final String DELETE_FAVOURITE_BUSINESS = "call wwj_db.delete_favourite_business(?, ?)";
	public static final String PUBLISH_OFFER = "call wwj_db.publish_offer(?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String GET_COMPANY_BRANCHES = "call wwj_db.retrieve_company_branches(?)";
	public static final String INSERT_JOB = "call wwj_db.insert_job(?, ?)";
	public static final String INSERT_REQUIREMENT = "call wwj_db.insert_requirement(?, ?)";
}
