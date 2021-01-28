package test.selenium;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author dominique toce
 */

public class TestToolbarSelenium {

	@Test
	public void testToolbarSetupLogged() {
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/WorldWideJob/login.jsp");
				
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("ralph@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("gatto");
		driver.findElement(By.xpath("/html/body/form/fieldset/div[3]/input")).click();	
		
		List<WebElement> anchors = driver.findElements(By.cssSelector(".dropdown-content a"));
		int count = anchors.size();
		
		driver.close();
		
		assertEquals(4, count); //Not logged should be 2
	}

}
