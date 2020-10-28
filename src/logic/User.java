package logic;

import java.util.Scanner;
import java.security.*;
import java.nio.charset.*;
import java.util.logging.*;

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
		MessageDigest digest;
	
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
		byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		
		return new String(encodedhash, StandardCharsets.UTF_8);
	}
	
	public static void main(String[] args) {
		
		System.out.println("Insert a username: ");		
		Scanner sc = new Scanner(System.in);
		String un = sc.next();
		
		System.out.println("Insert a password: ");
		String pwd = sc.next();
		
		sc.close();
		
		User me = new User(un, pwd);	
		
		String msg = "This is user " + me.getUsername() + " with password " + me.getPassword();
				
		Logger log = Logger.getLogger("logic.User");	
		log.log(Level.INFO, msg);
	}
}




