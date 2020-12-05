package logic.application;

public class SignUpControl {
	
	private static SignUpControl instance = null;

    private SignUpControl() {
    	/*Default constructor*/
    }

    public static SignUpControl getInstance() {
        if(instance == null) {
        	instance = new SignUpControl();
        }

        return instance;
    }
    
    /*DUMMY*/
    public void signUp(String email, String password, String firstName, String lastName) {
    	
    	/*Save account into the DB and redirect*/
    }
}
