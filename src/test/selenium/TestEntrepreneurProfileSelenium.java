package test.selenium;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestEntrepreneurProfileSelenium {

	@Test
	public void testToolbarSetupLogged() {
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/WorldWideJob/login.jsp");
				
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("tom@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("serpe");
		driver.findElement(By.xpath("/html/body/form/fieldset/div[3]/input")).click();	
		
		
		WebElement elem = driver.findElement(By.xpath("/html/body/div[2]/form/div[2]/h3"));
		String role = elem.getText();
		
		driver.close();
		
		assertEquals("Entrepreneur", role);
	}

}
