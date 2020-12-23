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
}
