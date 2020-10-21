import java.util.Scanner;
import java.security.*;
import java.nio.charset.*;

public class User {
	private String username;
	private String password;
	
	protected User(String un, String pwd) {
		this.username = un;
		this.password = pwd;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		MessageDigest digest = null;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		
		String pwd = new String(encodedhash);
		
		return pwd;
	}
	
	public static void main(String args[]) {
		
		System.out.println("Insert a username: ");		
		Scanner sc = new Scanner(System.in);
		String un = sc.next();
		
		System.out.println("Insert a password: ");
		String pwd = sc.next();
		
		sc.close();
		
		User me = new User(un, pwd);	
				
		System.out.println("This is user " + me.getUsername() + " with password " + me.getPassword());
	}
}
