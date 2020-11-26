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
    public void signUp(String email, String password) {
    	
        /*USAGE OF A DAO TO ACCESS THE DB:   
         * check if the user is already signed 
         * if so, throw exception
         */

    }
}
