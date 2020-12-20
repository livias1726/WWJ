package logic.application;

import java.util.HashMap;
import java.util.Map;

public enum Users {
	SEEKER(0),
	RECRUITER(1),
	ENTREPRENEUR(2),
	ADMIN(3);
	
	private int value;
	
	private static Map<Object, Object> map = new HashMap<>();

    private Users(int value) {
        this.value = value;
    }

    static {
        for (Users user : Users.values()) {
            map.put(user.value, user);
        }
    }

    public static Users valueOf(Integer user) {
        return (Users) map.get(user);
    }
    
	public int getValue() {
		return value;
	}
	
	public static Users stringToUsers(String in) {
		Users out;
		switch(in) {
			case "SEEKER":
				out = Users.SEEKER;
				break;
			case "RECRUITER":
				out = Users.RECRUITER;
				break;
			case "ENTREPRENEUR":
				out = Users.ENTREPRENEUR;
				break;
			case "ADMIN":
				out = Users.ADMIN;
				break;
			default:
				out = null;
				break;		
		}
		
		return out;
	}
	
	public static String usersToString(Users in) {
		String out;
		switch(in) {
			case SEEKER:
				out = "SEEKER";
				break;
			case RECRUITER:
				out = "RECRUITER";
				break;
			case ENTREPRENEUR:
				out = "ENTREPRENEUR";
				break;
			case ADMIN:
				out = "ADMIN";
				break;
			default:
				out = null;
				break;		
		}
		
		return out;
	}
}
