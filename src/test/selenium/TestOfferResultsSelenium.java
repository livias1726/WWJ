package test.selenium;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @author livia simoncini
 */

public class TestOfferResultsSelenium {

	@Test
	public void testSeekerRoleLabel() {
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/WorldWideJob/index.jsp");
		
		driver.findElement(By.xpath("/html/body/form/div[4]/button[1]")).click();
		
		Select search = new Select(driver.findElement(By.xpath("/html/body/div[2]/form/div/select[2]")));
		search.selectByVisibleText("Engineering");
		
		driver.findElement(By.xpath("/html/body/div[2]/form/div/button")).click();
				
		List<WebElement> res1 = driver.findElements(By.tagName("li"));
		String val1 = res1.get(0).getAttribute("value");
		
		Select order = new Select(driver.findElement(By.xpath("//*[@id=\"order_off\"]")));
		order.selectByVisibleText("Expiration");
		order.selectByVisibleText("Upload");
		
		List<WebElement> res2 = driver.findElements(By.tagName("li"));
		String val2 = res2.get(1).getAttribute("value");
				
		driver.close();
		
		assertEquals(val1, val2);
	}
}
